package com.github.cadecode.ubp.admin.serviceimpl;

import com.github.cadecode.ubp.admin.bean.data.SysPermissionRouteQueryDo;
import com.github.cadecode.ubp.admin.bean.po.SysPermission;
import com.github.cadecode.ubp.admin.bean.vo.SysPermissionRouteVo.SysPermissionRouteRespVo;
import com.github.cadecode.ubp.admin.convert.SysPermissionConvert;
import com.github.cadecode.ubp.admin.enums.PermissionTypeEnum;
import com.github.cadecode.ubp.admin.enums.RouteTypeEnum;
import com.github.cadecode.ubp.admin.mapper.SysPermissionMapper;
import com.github.cadecode.ubp.admin.service.SysPermissionService;
import com.github.cadecode.ubp.common.util.TreeUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限 服务层实现
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermissionRouteRespVo> convertPermissionsToRouteTree(List<SysPermissionRouteQueryDo> queryDoList) {
        // 转换
        List<SysPermissionRouteRespVo> routeRespVoList = SysPermissionConvert.INSTANCE.routeQueryDoToRouteRespVo(queryDoList);
        // 树形化
        return TreeUtil.listToTree(routeRespVoList, null, SysPermissionRouteRespVo::getId, SysPermissionRouteRespVo::getParentId,
                (p, c) -> {
                    List<String> currPermissions = new ArrayList<>();
                    List<SysPermissionRouteRespVo> children = c.stream()
                            // 收集当前菜单下的 api 权限
                            .peek(o -> {
                                if (o.getPermissionType() == PermissionTypeEnum.API) {
                                    currPermissions.add(o.getPermissionCode());
                                }
                            })
                            // 对外链特殊处理
                            .peek(o -> {
                                if (o.getRouteType() == RouteTypeEnum.EXTERNAL) {
                                    o.setName(o.getMeta().getFrameSrc());
                                }
                            })
                            // 过滤其他权限类型
                            .filter(o -> o.getPermissionType() == PermissionTypeEnum.ROUTE)
                            .toList();
                    p.getMeta().setAuths(currPermissions);
                    p.getChildren().addAll(children);
                });
    }
}
