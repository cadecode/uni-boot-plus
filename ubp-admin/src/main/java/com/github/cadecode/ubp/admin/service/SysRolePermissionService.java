package com.github.cadecode.ubp.admin.service;

import com.github.cadecode.ubp.admin.bean.po.SysRolePermission;
import com.github.cadecode.ubp.framework.starter.security.RolePermissionService;
import com.mybatisflex.core.service.IService;

/**
 * 角色权限关系 服务层
 *
 * @author Cade Li
 * @since 2024/5/08
 */
public interface SysRolePermissionService extends IService<SysRolePermission>, RolePermissionService {

}
