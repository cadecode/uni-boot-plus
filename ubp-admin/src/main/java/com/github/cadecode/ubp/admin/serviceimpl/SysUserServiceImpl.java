package com.github.cadecode.ubp.admin.serviceimpl;

import com.github.cadecode.ubp.admin.bean.po.SysUser;
import com.github.cadecode.ubp.admin.mapper.SysUserMapper;
import com.github.cadecode.ubp.admin.service.SysUserService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 系统用户 服务层实现
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

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
