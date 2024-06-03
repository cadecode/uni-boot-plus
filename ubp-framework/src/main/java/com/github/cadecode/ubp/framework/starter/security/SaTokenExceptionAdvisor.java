package com.github.cadecode.ubp.framework.starter.security;

import cn.dev33.satoken.error.SaErrorCode;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import com.github.cadecode.ubp.common.consts.ApiStatus;
import com.github.cadecode.ubp.common.enums.ErrorCode;
import com.github.cadecode.ubp.framework.enums.AuthErrorEnum;
import com.github.cadecode.ubp.starter.web.model.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 框架异常处理器
 *
 * @author Cade Li
 * @date 2022/5/30
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class SaTokenExceptionAdvisor {

    private static final Map<Integer, ErrorCode> SA_AUTH_ERROR_MAP = new HashMap<>();

    static {
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11001, AuthErrorEnum.TOKEN_NOT_EXIST);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11011, AuthErrorEnum.TOKEN_NOT_EXIST);

        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11012, AuthErrorEnum.TOKEN_ERROR);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11014, AuthErrorEnum.TOKEN_ERROR);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11015, AuthErrorEnum.TOKEN_ERROR);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11016, AuthErrorEnum.TOKEN_ERROR);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11061, AuthErrorEnum.TOKEN_ERROR);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11071, AuthErrorEnum.TOKEN_ERROR);

        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11013, AuthErrorEnum.TOKEN_EXPIRED);

        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11041, AuthErrorEnum.TOKEN_NO_AUTHORITY);
        SA_AUTH_ERROR_MAP.put(SaErrorCode.CODE_11051, AuthErrorEnum.TOKEN_NO_AUTHORITY);
    }

    /**
     * SaTokenException 统一拦截器
     */
    @ExceptionHandler(value = SaTokenException.class)
    public ApiResult<Object> handleSaTokenException(SaTokenException e, HttpServletRequest request) {
        log.error("Handle SaToken exception, uri:{} =>", request.getRequestURI(), e);
        // 获取或者生成 ErrorCode
        ErrorCode errorCode;
        if (SA_AUTH_ERROR_MAP.containsKey(e.getCode())) {
            errorCode = SA_AUTH_ERROR_MAP.get(e.getCode());
        } else {
            errorCode = new ErrorCode() {
                @Override
                public String getCode() {
                    return "SA_" + e.getCode();
                }

                @Override
                public String getMessage() {
                    return e.getMessage();
                }

                @Override
                public int getStatus() {
                    return ApiStatus.SERVER_ERROR;
                }
            };
        }
        String moreInfo = e.getMessage();
        if (e instanceof NotLoginException notLoginException) {
            moreInfo += ",loginType:" + notLoginException.getLoginType();
        }
        return ApiResult.error(errorCode)
                .moreInfo(moreInfo)
                .path(request.getRequestURI());
    }

}
