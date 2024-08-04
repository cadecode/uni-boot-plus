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
     * 权限 ID
     */
    @Id(keyType = KeyType.None)
    @Schema(description = "权限 ID")
    private String permissionId;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permissionName;

    /**
     * 权限描述
     */
    @Schema(description = "权限描述")
    private String description;

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
