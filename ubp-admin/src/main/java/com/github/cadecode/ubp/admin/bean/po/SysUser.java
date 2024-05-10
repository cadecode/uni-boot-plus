package com.github.cadecode.ubp.admin.bean.po;

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
 * 系统用户 实体类
 *
 * @author Cade Li
 * @since 2024/5/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统用户")
@Table("sys_user")
public class SysUser implements BaseFieldOperable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    @Id(keyType = KeyType.None)
    @Schema(description = "用户 ID")
    private String userId;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String userName;

    /**
     * 用户组 ID
     */
    @Schema(description = "用户组 ID")
    private String userGroupId;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enableFlag;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String sex;

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
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String loginIp;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private LocalDateTime loginDate;

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
