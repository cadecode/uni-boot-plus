package com.github.cadecode.ubp.admin.service;

import com.github.cadecode.ubp.admin.bean.data.SysPermissionRouteQueryDo;
import com.github.cadecode.ubp.admin.bean.po.SysPermission;
import com.github.cadecode.ubp.admin.bean.vo.SysPermissionRouteVo.SysPermissionRouteRespVo;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 系统权限 服务层
 *
 * @author Cade Li
 * @since 2024/5/08
 */
public interface SysPermissionService extends IService<SysPermission> {

    List<SysPermissionRouteRespVo> convertPermissionsToRouteTree(List<SysPermissionRouteQueryDo> queryDoList);
}
