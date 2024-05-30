package com.github.cadecode.ubp.admin.bean.po;

import com.github.cadecode.ubp.admin.enums.PermissionTypeEnum;
import com.github.cadecode.ubp.admin.enums.RouteTypeEnum;
import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统权限 实体类
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统权限")
@Table("sys_permission")
public class SysPermission implements BaseFieldOperable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Schema(description = "ID")
    private Long id;

    /**
     * 权限类型
     */
    @Schema(description = "权限类型")
    private PermissionTypeEnum permissionType;

    /**
     * 权限代码
     */
    @Schema(description = "权限代码")
    private String permissionCode;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permissionName;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;

    /**
     * 父级 ID
     */
    @Schema(description = "父级 ID")
    private Long parentId;

    /**
     * 路由类型
     */
    @Schema(description = "路由类型")
    private RouteTypeEnum routeType;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer orderNum;

    /**
     * 路由路径
     */
    @Schema(description = "路由路径")
    private String routePath;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String componentPath;

    /**
     * 路由重定向
     */
    @Schema(description = "路由重定向")
    private String redirect;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 菜单名称右侧的额外图标
     */
    @Schema(description = "菜单名称右侧的额外图标")
    private String extraIcon;

    /**
     * 是否展示菜单
     */
    @Schema(description = "是否展示菜单")
    private Boolean showLink;

    /**
     * 是否展示父级菜单
     */
    @Schema(description = "是否展示父级菜单")
    private Boolean showParent;

    /**
     * 是否启用缓存
     */
    @Schema(description = "是否启用缓存")
    private Boolean keepAlive;

    /**
     * iframe src
     */
    @Schema(description = "iframe src")
    private String frameSrc;

    /**
     * 是否启用 iframe loading
     */
    @Schema(description = "是否启用 iframe loading")
    private Boolean frameLoading;

    /**
     * 进场动画
     */
    @Schema(description = "进场动画")
    private String enterTransition;

    /**
     * 离场动画
     */
    @Schema(description = "离场动画")
    private String leaveTransition;

    /**
     * 是否展示标签
     */
    @Schema(description = "是否展示标签")
    private Boolean hiddenTag;

    /**
     * 最大标签数
     */
    @Schema(description = "最大标签数")
    private Integer dynamicLevel;

    /**
     * 激活路径
     */
    @Schema(description = "激活路径")
    private String activePath;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateUser;

}
