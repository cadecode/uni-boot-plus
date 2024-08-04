package com.github.cadecode.ubp.admin.bean.entity;

import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
 * 系统用户组 实体类
 *
 * @author Cade Li
 * @since 2024/5/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统用户组")
@Table("sys_user_group")
public class SysUserGroup implements BaseFieldOperable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户组 ID
     */
    @Id(keyType = KeyType.None)
    @Schema(description = "用户组 ID")
    private String userGroupId;

    /**
     * 父级 ID
     */
    @Schema(description = "父级 ID")
    private String parentGroupId;

    /**
     * 用户组名称
     */
    @Schema(description = "用户组名称")
    private String userGroupName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer orderNum;

    /**
     * 祖先部门
     */
    @Schema(description = "祖先部门")
    private String ancestors;

    /**
     * 领导名
     */
    @Schema(description = "领导名")
    private String leader;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String mail;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String phone;

    /**
     * 第一租户标识（作用于增删改查）
     */
    @Schema(description = "第一租户标识（作用于增删改查）")
    private String firstTenant;

    /**
     * 其他租户标识（作用于删改查）
     */
    @Schema(description = "其他租户标识（作用于删改查）")
    private String otherTenants;

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
