package com.github.cadecode.ubp.admin.service;

import com.github.cadecode.ubp.admin.bean.data.SysPermissionRouteQueryDo;
import com.github.cadecode.ubp.admin.bean.po.SysRolePermission;
import com.github.cadecode.ubp.framework.starter.security.RolePermissionService;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 角色权限关系 服务层
 *
 * @author Cade Li
 * @since 2024/5/08
 */
public interface SysRolePermissionService extends IService<SysRolePermission>, RolePermissionService {


    /**
     * 查询用户路由权限
     * @param username 用户名
     * @return 路由权限
     */
    List<SysPermissionRouteQueryDo> listRoutePermissionsByUsername(String username);

}
