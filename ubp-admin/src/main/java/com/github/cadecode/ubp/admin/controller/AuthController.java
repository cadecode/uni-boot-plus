package com.github.cadecode.ubp.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.NetUtil;
import com.github.cadecode.ubp.admin.bean.po.SysUser;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo.SysUserLoginReqVo;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo.SysUserLoginRespVo;
import com.github.cadecode.ubp.admin.convert.SysUserConvert;
import com.github.cadecode.ubp.admin.service.SysUserService;
import com.github.cadecode.ubp.framework.enums.AuthErrorEnum;
import com.github.cadecode.ubp.starter.security.encrypt.PasswordEncryptor;
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

    private final PasswordEncryptor passwordEncryptor;

    private final SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @return ApiResult
     */
    @SaIgnore
    @PostMapping("login")
    @Operation(summary = "用户登录")
    public ApiResult<SysUserLoginRespVo> login(@RequestBody @Valid SysUserLoginReqVo reqVo) {
        // 查询数据库
        SysUser sysUser = sysUserService.queryChain().eq(SysUser::getUserId, reqVo.getUserId()).one();
        if (Objects.isNull(sysUser)) {
            return ApiResult.<SysUserLoginRespVo>of(AuthErrorEnum.TOKEN_CREATE_ERROR, null).moreInfo("用户不存在");
        }
        // 判断是否启用账户
        if (Objects.equals(sysUser.getEnableFlag(), false)) {
            return ApiResult.<SysUserLoginRespVo>of(AuthErrorEnum.TOKEN_CREATE_ERROR, null).moreInfo("账号状态关闭");
        }
        // 校验密码
        if (!passwordEncryptor.validate(sysUser.getPassword(), null, reqVo.getPassword())) {
            return ApiResult.<SysUserLoginRespVo>of(AuthErrorEnum.TOKEN_CREATE_ERROR, null).moreInfo("密码错误");
        }
        // 登录
        StpUtil.login(reqVo.getUserId(), reqVo.getRememberMe());

        // 设置登录时间 IP 信息
        sysUser.setLoginIp(NetUtil.getLocalhost().getHostAddress());
        sysUser.setLoginDate(LocalDateTime.now());

        SysUserLoginRespVo sysUserLoginRespVo = SysUserConvert.INSTANCE.poToLoginRespVo(sysUser);

        // 获取角色权限
        List<String> roles = StpUtil.getRoleList(reqVo.getUserId());
        List<String> permissions = StpUtil.getPermissionList(reqVo.getUserId());
        sysUserLoginRespVo.setRoles(roles);
        sysUserLoginRespVo.setPermissions(permissions);

        // 获取 token 相关信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        sysUserLoginRespVo.setTokenInfo(tokenInfo);

        // 异步更新用户信息
        sysUserService.updateUserLoginInfoAsync(sysUser);
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
    public Object getUserRoutes() {
        // TODO 获取当前用户路由权限
        return null;
    }
}
