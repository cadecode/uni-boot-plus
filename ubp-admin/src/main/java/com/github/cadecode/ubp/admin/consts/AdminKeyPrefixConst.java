package com.github.cadecode.ubp.admin.consts;

/**
 * admin 模块缓存 key 前缀常量
 *
 * @author Cade Li
 * @since 2024/6/7
 */
public interface AdminKeyPrefixConst {

    /**
     * 用户名 - 角色
     */
    String USER_ROLES = "ubp-admin:user-roles";

    /**
     * 角色代码 - 权限
     */
    String ROLE_PERMISSIONS = "ubp-admin:role-permissions";

    /**
     * 用户名 - 菜单权限
     */
    String USER_ROUTES = "ubp-admin:user-routes";
}
