package com.github.cadecode.ubp.admin.convert;

import com.github.cadecode.ubp.admin.bean.data.SysPermissionRouteQueryDo;
import com.github.cadecode.ubp.admin.bean.vo.SysPermissionRouteVo.SysPermissionRouteMeta;
import com.github.cadecode.ubp.admin.bean.vo.SysPermissionRouteVo.SysPermissionRouteRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统权限 转换类
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Mapper
public interface SysPermissionConvert {

    SysPermissionConvert INSTANCE = Mappers.getMapper(SysPermissionConvert.class);

    @Mapping(target = "auths", ignore = true)
    @Mapping(target = "transition", source = "bo")
    @Mapping(target = "title", source = "permissionName")
    @Mapping(target = "rank", source = "orderNum")
    SysPermissionRouteMeta routeQueryDoToRouteMeta(SysPermissionRouteQueryDo bo);

    @Mapping(target = "name", source = "permissionCode")
    @Mapping(target = "meta", source = "bo")
    @Mapping(target = "path", source = "routePath")
    @Mapping(target = "component", source = "componentPath")
    @Mapping(target = "children", ignore = true)
    SysPermissionRouteRespVo routeQueryDoToRouteRespVo(SysPermissionRouteQueryDo bo);

    List<SysPermissionRouteRespVo> routeQueryDoToRouteRespVo(List<SysPermissionRouteQueryDo> boList);

}
