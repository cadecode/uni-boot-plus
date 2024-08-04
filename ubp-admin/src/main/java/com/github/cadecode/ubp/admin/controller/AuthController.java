package com.github.cadecode.ubp.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.NetUtil;
import com.github.cadecode.ubp.admin.bean.dto.SysUserLoginDto;
import com.github.cadecode.ubp.admin.bean.entity.SysUser;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo;
import com.github.cadecode.ubp.admin.convert.SysUserConvert;
import com.github.cadecode.ubp.admin.service.SysUserService;
import com.github.cadecode.ubp.framework.enums.AuthErrorEnum;
import com.github.cadecode.ubp.starter.web.annotation.ApiFormat;
import com.github.cadecode.ubp.starter.web.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 登录校验 控制层
 * <p>auth 相关接口免登录（部分）
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

    private final SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @return ApiResult
     */
    @SaIgnore
    @PostMapping("login")
    @Operation(summary = "用户登录")
    public ApiResult<SysUserLoginVo> login(@RequestBody @Valid SysUserLoginDto loginDto) {
        // 根据用户名查询
        SysUser sysUser = sysUserService.getUserByUserId(loginDto.getUserId());
        // check
        ApiResult<SysUserLoginVo> checkResult = sysUserService.checkLoginUser(loginDto, sysUser);
        if (Objects.nonNull(checkResult)) {
            return checkResult;
        }
        // 登录并获取 token
        StpUtil.login(loginDto.getUserId(), loginDto.getRememberMe());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 异步更新用户信息
        sysUser.setLoginIp(NetUtil.getLocalhost().getHostAddress());
        sysUser.setLoginDate(LocalDateTime.now());
        sysUserService.updateUserLoginInfoAsync(sysUser);

        SysUserLoginVo sysUserLoginVo = SysUserConvert.INSTANCE.entityToLoginVo(sysUser);
        // 填充角色、权限
        sysUserLoginVo.setRoles(StpUtil.getRoleList(loginDto.getUserId()));
        sysUserLoginVo.setPermissions(StpUtil.getPermissionList(loginDto.getUserId()));
        // 填充 token 相关信息
        sysUserLoginVo.setTokenInfo(tokenInfo);
        return ApiResult.ok(sysUserLoginVo);
    }

    /**
     * 判断当前用户是否登录
     *
     * @return 是否登录
     */
    @SaIgnore
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
    @SaIgnore
    @GetMapping("logout")
    @Operation(summary = "注销当前用户")
    public ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.error(AuthErrorEnum.TOKEN_LOGOUT);
    }

    /**
     * 获取当前用户路由权限
     * <p>需要登录
     *
     * @return 用户路由权限
     */
    @SaCheckLogin
    @GetMapping("get_user_routes")
    @Operation(summary = "获取当前用户路由权限")
    public Object getUserRoutes() {
        // TODO 获取当前用户路由权限
        return null;
    }
}
