package com.github.cadecode.ubp.admin.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 系统用户登录 DTO
 *
 * @author Cade Li
 * @date 2024/8/4
 */
@Schema(description = "系统用户登录 DTO")
@Data
public class SysUserLoginDto {
    @NotNull
    @Schema(description = "用户 ID")
    private String userId;
    @NotNull
    @Schema(description = "密码")
    private String password;
    @NotNull
    @Schema(description = "记住我")
    private Boolean rememberMe;
}
