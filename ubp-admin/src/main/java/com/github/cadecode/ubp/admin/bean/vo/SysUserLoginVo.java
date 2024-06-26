package com.github.cadecode.ubp.admin.bean.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统用户 VO 登录
 *
 * @author Cade Li
 * @since 2024/6/1
 */
public class SysUserLoginVo {

    @Schema(description = "系统用户 VO 登录请求")
    @Data
    public static class SysUserLoginReqVo {
        @NotNull
        @Schema(description = "用户名")
        private String username;
        @NotNull
        @Schema(description = "密码")
        private String password;
        @NotNull
        @Schema(description = "记住我")
        private Boolean rememberMe;
    }

    @Schema(description = "系统用户 VO 登录响应")
    @Data
    public static class SysUserLoginRespVo {
        @Schema(description = "ID")
        private Long id;
        @Schema(description = "用户名")
        private String username;
        @Schema(description = "昵称")
        private String nickname;
        @Schema(description = "用户组 ID")
        private Long groupId;
        @Schema(description = "状态")
        private Boolean status;
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

}
