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
     * 根据用户 ID 查询角色列表
     * @param loginId 用户 ID
     * @return 角色 ID 列表
     */
    List<String> listRoleIdByLoginId(Object loginId);

    /**
     * 根据角色 ID 查询权限列表
     * @param roleId 角色 ID
     * @return 权限 ID 列表
     */
    List<String> listPermissionIdByRoleId(String roleId);

    /**
     * 根据角色 ID 列表查询权限列表
     * @param roleIds 角色 ID 列表
     * @return 权限 ID 列表
     */
    List<String> listPermissionIdByRoleId(List<String> roleIds);
}
