package com.github.cadecode.ubp.framework.starter.security;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
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

    public static final String ROLE_LIST_KEY = "role_list";
    public static final String PERMISSION_LIST_KEY = "permission_list";

    private final RolePermissionService rolePermissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> permissionList = new ArrayList<>();
        List<String> roleList = getRoleList(loginId, loginType);
        for (String roleId : roleList) {
            SaSession roleCustomSession = SaSessionCustomUtil.getSessionById("role-" + roleId);
            List<String> list = roleCustomSession.get(PERMISSION_LIST_KEY, () -> rolePermissionService.listPermissionIdByRoleId(roleId));
            permissionList.addAll(list);
        }
        return permissionList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession accountSession = StpUtil.getSessionByLoginId(loginId);
        return accountSession.get(ROLE_LIST_KEY, () -> rolePermissionService.listRoleIdByLoginId(loginId));
    }

}

