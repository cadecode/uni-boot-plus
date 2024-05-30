package com.github.cadecode.ubp.admin.bean.po;

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
     * ID
     */
    @Id
    @Schema(description = "ID")
    private Long id;

    /**
     * 用户组名
     */
    @Schema(description = "用户组名")
    private String groupName;

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
     * 排序
     */
    @Schema(description = "排序")
    private Integer orderNum;

    /**
     * 祖先 ID
     */
    @Schema(description = "祖先 ID")
    private String ancestors;

    /**
     * 领导
     */
    @Schema(description = "领导")
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
