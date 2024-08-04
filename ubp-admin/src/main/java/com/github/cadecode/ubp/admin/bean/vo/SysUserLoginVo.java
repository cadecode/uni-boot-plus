package com.github.cadecode.ubp.admin.bean.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统用户登录 VO
 *
 * @author Cade Li
 * @since 2024/6/1
 */
@Schema(description = "系统用户登录 VO")
@Data
public class SysUserLoginVo {
    @Schema(description = "用户 ID")
    private String userId;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "用户名称")
    private String userName;
    @Schema(description = "用户组 ID")
    private String userGroupId;
    @Schema(description = "是否启用")
    private Boolean enableFlag;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "性别")
    private String sex;
    @Schema(description = "邮箱")
    private String mail;
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "登录IP")
    private String loginIp;
    @Schema(description = "登录时间")
    private LocalDateTime loginDate;
    @Schema(description = "Token 信息")
    private SaTokenInfo tokenInfo;
    @Schema(description = "角色")
    List<String> roles;
    @Schema(description = "权限")
    List<String> permissions;
}
