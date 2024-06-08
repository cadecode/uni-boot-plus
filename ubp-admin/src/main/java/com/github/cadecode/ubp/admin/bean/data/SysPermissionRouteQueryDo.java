package com.github.cadecode.ubp.admin.bean.data;

import com.github.cadecode.ubp.admin.enums.PermissionTypeEnum;
import com.github.cadecode.ubp.admin.enums.RouteTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 系统权限 路由 QueryDO
 *
 * @author Cade Li
 * @since 2024/6/8
 */
@Data
public class SysPermissionRouteQueryDo {
    private Long id;
    private PermissionTypeEnum permissionType;
    private String permissionCode;
    private String permissionName;
    private Boolean status;
    private Long parentId;
    private RouteTypeEnum routeType;
    private Integer orderNum;
    private String routePath;
    private String componentPath;
    private String redirect;
    private String icon;
    private String extraIcon;
    private Boolean showLink;
    private Boolean showParent;
    private Boolean keepAlive;
    private String frameSrc;
    private Boolean frameLoading;
    private String enterTransition;
    private String leaveTransition;
    private Boolean hiddenTag;
    private Integer dynamicLevel;
    private String activePath;
    private String remark;

    private List<String> roles;
}
