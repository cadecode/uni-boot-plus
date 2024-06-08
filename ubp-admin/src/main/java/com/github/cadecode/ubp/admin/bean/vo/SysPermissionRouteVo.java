package com.github.cadecode.ubp.admin.bean.vo;

import com.github.cadecode.ubp.admin.enums.PermissionTypeEnum;
import com.github.cadecode.ubp.admin.enums.RouteTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限 VO 路由
 *
 * @author Cade Li
 * @since 2024/6/7
 */
public class SysPermissionRouteVo {

    @Schema(description = "系统权限 VO 路由响应")
    @Data
    public static class SysPermissionRouteRespVo {
        @Schema(description = "ID")
        private Long id;
        @Schema(description = "权限类型")
        private PermissionTypeEnum permissionType;
        @Schema(description = "权限代码")
        private String permissionCode;
        @Schema(description = "状态")
        private Boolean status;
        @Schema(description = "父级 ID")
        private Long parentId;
        @Schema(description = "路由类型")
        private RouteTypeEnum routeType;
        @Schema(description = "备注")
        private String remark;

        @Schema(description = "路由名称")
        private String name;
        @Schema(description = "路由路径")
        private String path;
        @Schema(description = "组件路径")
        private String component;
        @Schema(description = "路由重定向")
        private String redirect;
        @Schema(description = "系统权限路由元信息")
        private SysPermissionRouteMeta meta;

        private List<SysPermissionRouteRespVo> children = new ArrayList<>();

    }

    @Data
    public static class SysPermissionRouteMeta {
        @Schema(description = "菜单名称")
        private String title;
        @Schema(description = "排序")
        private Integer rank;
        @Schema(description = "菜单图标")
        private String icon;
        @Schema(description = "菜单名称右侧的额外图标")
        private String extraIcon;
        @Schema(description = "是否展示菜单")
        private Boolean showLink;
        @Schema(description = "是否展示父级菜单")
        private Boolean showParent;
        @Schema(description = "是否启用缓存")
        private Boolean keepAlive;
        @Schema(description = "iframe src")
        private String frameSrc;
        @Schema(description = "是否启用 iframe loading")
        private Boolean frameLoading;
        @Schema(description = "是否展示标签")
        private Boolean hiddenTag;
        @Schema(description = "最大标签数")
        private Integer dynamicLevel;
        @Schema(description = "激活路径")
        private String activePath;
        @Schema(description = "角色")
        private List<String> roles;
        @Schema(description = "权限")
        private List<String> auths;

        @Schema(description = "系统权限路由过渡动画")
        private SysPermissionRouteMetaTransition transition;
    }

    @Data
    public static class SysPermissionRouteMetaTransition {
        @Schema(description = "进场动画")
        private String enterTransition;
        @Schema(description = "离场动画")
        private String leaveTransition;
    }
}
