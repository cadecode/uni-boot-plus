package com.github.cadecode.ubp.framework.starter.security;

import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  自定义权限加载接口实现类
 *
 * @author Cade Li
 * @since 2024/5/7
 */
@RequiredArgsConstructor
@Component
public class StpInterfaceImpl implements StpInterface {

    private final RolePermissionResolvable rolePermissionResolvable;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        List<String> roleList = getRoleList(loginId, loginType);
        for (String role : roleList) {
            List<String> permissions = rolePermissionResolvable.listPermissionIdByRoleId(role);
            permissionList.addAll(permissions);
        }
        return permissionList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return rolePermissionResolvable.listRoleIdByLoginId(loginId);
    }

}

