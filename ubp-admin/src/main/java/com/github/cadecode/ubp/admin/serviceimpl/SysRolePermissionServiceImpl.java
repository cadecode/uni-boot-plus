package com.github.cadecode.ubp.admin.serviceimpl;

import com.github.cadecode.ubp.admin.bean.po.SysPermission;
import com.github.cadecode.ubp.admin.bean.po.SysRole;
import com.github.cadecode.ubp.admin.bean.po.SysRolePermission;
import com.github.cadecode.ubp.admin.enums.PermissionTypeEnum;
import com.github.cadecode.ubp.admin.mapper.SysRolePermissionMapper;
import com.github.cadecode.ubp.admin.service.SysRolePermissionService;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
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

    @Override
    public List<String> listRolesByLoginId(Object loginId) {
        return QueryChain.of(SysRole.class)
                .select(SYS_ROLE.ROLE_CODE)
                .from(SYS_ROLE)
                .innerJoin(SYS_ROLE_USER).on(SYS_ROLE_USER.ROLE_ID.eq(SYS_ROLE.ID))
                .innerJoin(SYS_USER).on(SYS_ROLE_USER.USER_ID.eq(SYS_USER.ID))
                .where(SYS_USER.USERNAME.eq(loginId))
                .and(SYS_ROLE.STATUS.eq(true))
                .listAs(String.class);
    }

    @Override
    public List<String> listPermissionsByRole(String roleCode) {
        return listPermissionsByRole(List.of(roleCode));
    }

    /**
     * 不包含路由权限
     */
    @Override
    public List<String> listPermissionsByRole(List<String> roleCodes) {
        return QueryChain.of(SysPermission.class)
                .select(SYS_PERMISSION.PERMISSION_CODE)
                .from(SYS_PERMISSION)
                .innerJoin(SYS_ROLE_PERMISSION).on(SYS_ROLE_PERMISSION.PERMISSION_ID.eq(SYS_PERMISSION.ID))
                .innerJoin(SYS_ROLE).on(SYS_ROLE_PERMISSION.ROLE_ID.eq(SYS_ROLE.ID))
                .where(SYS_ROLE.ROLE_CODE.in(roleCodes))
                .and(SYS_PERMISSION.PERMISSION_TYPE.ne(PermissionTypeEnum.ROUTE))
                .and(SYS_PERMISSION.STATUS.eq(true))
                .listAs(String.class);
    }
}
