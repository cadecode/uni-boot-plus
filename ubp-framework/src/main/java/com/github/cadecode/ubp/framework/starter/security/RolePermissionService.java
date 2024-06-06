package com.github.cadecode.ubp.framework.starter.security;

import java.util.List;

/**
 * 角色权限 服务接口
 *
 * @author Cade Li
 * @since 2024/5/8
 */
public interface RolePermissionService {

    /**
     * 根据用户登录 ID （用户名）查询角色列表
     * @param loginId 用户登录 ID
     * @return 角色代码列表
     */
    List<String> listRolesByLoginId(Object loginId);

    /**
     * 根据角色代码查询权限列表
     * @param roleCode 角色代码
     * @return 权限代码列表
     */
    List<String> listPermissionsByRole(String roleCode);

    /**
     * 根据角色代码列表查询权限列表
     * @param roleCodes 角色代码
     * @return 权限代码列表
     */
    List<String> listPermissionsByRole(List<String> roleCodes);
}
