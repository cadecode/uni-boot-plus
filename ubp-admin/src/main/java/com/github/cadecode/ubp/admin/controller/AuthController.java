package com.github.cadecode.ubp.admin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.github.cadecode.ubp.admin.bean.po.SysUser;
import com.github.cadecode.ubp.admin.service.SysUserService;
import com.github.cadecode.ubp.common.exception.GeneralException;
import com.github.cadecode.ubp.framework.enums.AuthErrorEnum;
import com.github.cadecode.ubp.starter.security.encrypt.PasswordEncryptor;
import com.github.cadecode.ubp.starter.web.annotation.ApiFormat;
import com.github.cadecode.ubp.starter.web.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 登录校验 控制层
 *
 * @author Cade Li
 * @since 2024/5/10
 */
@ApiFormat
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "登录校验接口")
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncryptor passwordEncryptor;

    private final SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @return ApiResult
     */
    @PostMapping("login")
    @Operation(summary = "用户登录")
    public SaTokenInfo login(@RequestParam @Parameter(description = "用户 ID") String userId,
                             @RequestParam @Parameter(description = "密码") String password,
                             @RequestParam @Parameter(description = "是否记住我") Boolean rememberMe) {
        // 1. 查询数据库
        SysUser sysUser = sysUserService.getById(userId);
        if (Objects.isNull(sysUser)) {
            throw GeneralException.of(AuthErrorEnum.TOKEN_CREATE_ERROR, "该用户不存在");
        }
        // 2. 判断是否启用账户
        if (Objects.equals(sysUser.getEnableFlag(), false)) {
            throw GeneralException.of(AuthErrorEnum.TOKEN_CREATE_ERROR, "账号已被关闭");
        }
        // 3. 校验密码
        if (!passwordEncryptor.validate(sysUser.getPassword(), null, password)) {
            throw GeneralException.of(AuthErrorEnum.TOKEN_CREATE_ERROR, "密码错误");
        }
        // 4. 登录
        StpUtil.login(userId, rememberMe);
        // 5. 获取 token 相关信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 6. 返回 token 信息
        return tokenInfo;
    }

    /**
     * 判断当前用户是否登录
     *
     * @return 是否登录
     */
    @GetMapping("is_login")
    @Operation(summary = "判断当前用户是否登录")
    public Boolean isLogin() {
        return StpUtil.isLogin();
    }

    /**
     * 注销当前用户
     *
     * @return ApiResult, 用户主动注销
     */
    @GetMapping("logout")
    @Operation(summary = "注销当前用户")
    public ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.error(AuthErrorEnum.TOKEN_LOGOUT);
    }

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("get_user_info")
    @Operation(summary = "获取当前用户信息")
    public Object getUserInfo() {
        // TODO 获取当前用户信息
        return null;
    }
}
