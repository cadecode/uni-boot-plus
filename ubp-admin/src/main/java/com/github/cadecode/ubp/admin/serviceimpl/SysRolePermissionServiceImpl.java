package com.github.cadecode.ubp.admin.serviceimpl;

import com.github.cadecode.ubp.admin.bean.po.SysPermission;
import com.github.cadecode.ubp.admin.bean.po.SysRole;
import com.github.cadecode.ubp.admin.bean.po.SysRolePermission;
import com.github.cadecode.ubp.admin.mapper.SysRolePermissionMapper;
import com.github.cadecode.ubp.admin.service.SysRolePermissionService;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.cadecode.ubp.admin.bean.po.table.SysPermissionTableDef.SYS_PERMISSION;
import static com.github.cadecode.ubp.admin.bean.po.table.SysRolePermissionTableDef.SYS_ROLE_PERMISSION;
import static com.github.cadecode.ubp.admin.bean.po.table.SysRoleTableDef.SYS_ROLE;
import static com.github.cadecode.ubp.admin.bean.po.table.SysRoleUserTableDef.SYS_ROLE_USER;
import static com.github.cadecode.ubp.admin.bean.po.table.SysUserTableDef.SYS_USER;

/**
 * 角色权限关系 服务层实现
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Cacheable(cacheNames = "sys-role-permission:user-roles", key = "#loginId")
    @Override
    public List<String> listRoleIdByLoginId(Object loginId) {
        return QueryChain.of(SysRole.class)
                .select(SYS_ROLE.ROLE_ID)
                .from(SYS_ROLE)
                .innerJoin(SYS_ROLE_USER).on(SYS_ROLE_USER.ROLE_ID.eq(SYS_ROLE.ROLE_ID))
                .innerJoin(SYS_USER).on(SYS_ROLE_USER.USER_ID.eq(SYS_USER.USER_ID))
                .where(SYS_USER.USER_ID.eq(loginId))
                .listAs(String.class);
    }

    @Cacheable(cacheNames = "sys-role-permission:role-permissions", key = "#roleId")
    @Override
    public List<String> listPermissionIdByRoleId(String roleId) {
        return listPermissionIdByRoleId(List.of(roleId));
    }

    @Override
    public List<String> listPermissionIdByRoleId(List<String> roleIds) {
        return QueryChain.of(SysPermission.class)
                .select(SYS_PERMISSION.PERMISSION_ID)
                .from(SYS_PERMISSION)
                .innerJoin(SYS_ROLE_PERMISSION).on(SYS_ROLE_PERMISSION.PERMISSION_ID.eq(SYS_PERMISSION.PERMISSION_ID))
                .innerJoin(SYS_ROLE).on(SYS_ROLE_PERMISSION.ROLE_ID.eq(SYS_ROLE.ROLE_ID))
                .where(SYS_ROLE.ROLE_ID.in(roleIds))
                .listAs(String.class);
    }
}
