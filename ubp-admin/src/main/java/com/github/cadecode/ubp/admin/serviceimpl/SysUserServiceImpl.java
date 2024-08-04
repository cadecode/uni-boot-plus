package com.github.cadecode.ubp.admin.serviceimpl;

import com.github.cadecode.ubp.admin.bean.dto.SysUserLoginDto;
import com.github.cadecode.ubp.admin.bean.entity.SysUser;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo;
import com.github.cadecode.ubp.admin.mapper.SysUserMapper;
import com.github.cadecode.ubp.admin.service.SysUserService;
import com.github.cadecode.ubp.framework.enums.AuthErrorEnum;
import com.github.cadecode.ubp.starter.security.encrypt.PasswordEncryptor;
import com.github.cadecode.ubp.starter.web.model.ApiResult;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 系统用户 服务层实现
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncryptor passwordEncryptor;

    @Override
    public SysUser getUserByUserId(String userId) {
        return queryChain().eq(SysUser::getUserId, userId).one();
    }

    @Override
    public ApiResult<SysUserLoginVo> checkLoginUser(SysUserLoginDto reqVo, SysUser sysUser) {
        if (Objects.isNull(sysUser)) {
            return ApiResult.<SysUserLoginVo>of(AuthErrorEnum.TOKEN_CREATE_ERROR, null).moreInfo("用户不存在");
        }
        // 判断是否启用账户
        if (Objects.equals(sysUser.getEnableFlag(), false)) {
            return ApiResult.<SysUserLoginVo>of(AuthErrorEnum.TOKEN_CREATE_ERROR, null).moreInfo("账号状态关闭");
        }
        // 校验密码
        if (!passwordEncryptor.validate(sysUser.getPassword(), null, reqVo.getPassword())) {
            return ApiResult.<SysUserLoginVo>of(AuthErrorEnum.TOKEN_CREATE_ERROR, null).moreInfo("密码错误");
        }
        return null;
    }

    @Override
    public void updateUserLoginInfo(SysUser sysUser) {
        updateChain()
                .set(SysUser::getLoginIp, sysUser.getLoginIp())
                .set(SysUser::getLoginDate, sysUser.getLoginDate())
                .where(SysUser::getUserId).eq(sysUser.getUserId())
                .update();
    }

    @Async
    @Override
    public void updateUserLoginInfoAsync(SysUser sysUser) {
        updateUserLoginInfo(sysUser);
    }
}
