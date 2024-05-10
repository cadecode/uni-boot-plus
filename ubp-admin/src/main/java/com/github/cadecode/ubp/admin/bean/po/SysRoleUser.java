package com.github.cadecode.ubp.admin.bean.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色用户关系 实体类
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色用户关系")
@Table("sys_role_user")
public class SysRoleUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "角色 ID")
    private String roleId;

    @Id
    @Schema(description = "用户 ID")
    private String userId;

}
