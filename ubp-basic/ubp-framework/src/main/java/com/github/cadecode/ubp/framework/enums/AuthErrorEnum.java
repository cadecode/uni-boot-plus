package com.github.cadecode.ubp.framework.enums;

import com.github.cadecode.ubp.common.consts.ApiStatus;
import com.github.cadecode.ubp.common.enums.ErrorCode;
import lombok.Getter;

/**
 * 认证授权错误码枚举
 *
 * @author Cade Li
 * @since 2022/5/8
 */
@Getter
public enum AuthErrorEnum implements ErrorCode {

    TOKEN_NOT_EXIST("AUTH_1000", "未登录") {
        @Override
        public int getStatus() {
            return ApiStatus.NO_AUTHENTICATION;
        }
    },
    TOKEN_ERROR("AUTH_1001", "Token 错误") {
        @Override
        public int getStatus() {
            return ApiStatus.NO_AUTHENTICATION;
        }
    },
    TOKEN_EXPIRED("AUTH_1002", "Token 已过期") {
        @Override
        public int getStatus() {
            return ApiStatus.NO_AUTHENTICATION;
        }
    },
    TOKEN_NO_AUTHORITY("AUTH_1003", "权限不足") {
        @Override
        public int getStatus() {
            return ApiStatus.NO_AUTHORITY;
        }
    },
    TOKEN_CREATE_ERROR("AUTH_1004", "登录失败") {
        @Override
        public int getStatus() {
            return ApiStatus.OK;
        }
    },
    TOKEN_LOGOUT("AUTH_1005", "用户主动注销") {
        @Override
        public int getStatus() {
            return ApiStatus.NO_AUTHENTICATION;
        }
    },
    ;

    private final String code;

    private final String message;

    AuthErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
