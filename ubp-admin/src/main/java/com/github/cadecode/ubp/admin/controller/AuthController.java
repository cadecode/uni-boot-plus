package com.github.cadecode.ubp.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.NetUtil;
import com.github.cadecode.ubp.admin.bean.data.SysPermissionRouteQueryDo;
import com.github.cadecode.ubp.admin.bean.po.SysUser;
import com.github.cadecode.ubp.admin.bean.vo.SysPermissionRouteVo.SysPermissionRouteRespVo;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo.SysUserLoginReqVo;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo.SysUserLoginRespVo;
import com.github.cadecode.ubp.admin.convert.SysUserConvert;
import com.github.cadecode.ubp.admin.service.SysPermissionService;
import com.github.cadecode.ubp.admin.service.SysRolePermissionService;
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
import java.util.List;
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

    private final SysPermissionService sysPermissionService;

    private final SysRolePermissionService sysRolePermissionService;

    /**
     * 用户登录
     *
     * @return ApiResult
     */
    @SaIgnore
    @PostMapping("login")
    @Operation(summary = "用户登录")
    public ApiResult<SysUserLoginRespVo> login(@RequestBody @Valid SysUserLoginReqVo reqVo) {
        // 根据用户名查询
        SysUser sysUser = sysUserService.getUserByUsername(reqVo.getUsername());
        // check
        ApiResult<SysUserLoginRespVo> checkResult = sysUserService.checkLoginUser(reqVo, sysUser);
        if (Objects.nonNull(checkResult)) {
            return checkResult;
        }
        // 登录并获取 token
        StpUtil.login(reqVo.getUsername(), reqVo.getRememberMe());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 异步更新用户信息
        sysUser.setLoginIp(NetUtil.getLocalhost().getHostAddress());
        sysUser.setLoginDate(LocalDateTime.now());
        sysUserService.updateUserLoginInfoAsync(sysUser);

        SysUserLoginRespVo sysUserLoginRespVo = SysUserConvert.INSTANCE.poToLoginRespVo(sysUser);
        // 填充角色、权限
        sysUserLoginRespVo.setRoles(StpUtil.getRoleList(reqVo.getUsername()));
        sysUserLoginRespVo.setPermissions(StpUtil.getPermissionList(reqVo.getUsername()));
        // 填充 token 相关信息
        sysUserLoginRespVo.setTokenInfo(tokenInfo);
        return ApiResult.ok(sysUserLoginRespVo);
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
    public List<SysPermissionRouteRespVo> getUserRoutes() {
        // 根据用户名查询路由权限
        String username = StpUtil.getLoginIdAsString();
        List<SysPermissionRouteQueryDo> routeQueryDoList = sysRolePermissionService.listRoutePermissionsByUsername(username);
        return sysPermissionService.convertPermissionsToRouteTree(routeQueryDoList);
    }
}
