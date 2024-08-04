package com.github.cadecode.ubp.admin.service;

import com.github.cadecode.ubp.admin.bean.dto.SysUserLoginDto;
import com.github.cadecode.ubp.admin.bean.entity.SysUser;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo;
import com.github.cadecode.ubp.starter.web.model.ApiResult;
import com.mybatisflex.core.service.IService;

/**
 * 系统用户 服务层
 *
 * @author Cade Li
 * @since 2024/5/08
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getUserByUserId(String userId);

    ApiResult<SysUserLoginVo> checkLoginUser(SysUserLoginDto reqVo, SysUser sysUser);

    void updateUserLoginInfo(SysUser sysUser);

    void updateUserLoginInfoAsync(SysUser sysUser);

}
