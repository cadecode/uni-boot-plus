package com.github.cadecode.ubp.starter.web.advisor;

import com.github.cadecode.ubp.common.enums.ErrorCode;
import com.github.cadecode.ubp.common.exception.GeneralException;
import com.github.cadecode.ubp.starter.web.enums.WebErrorEnum;
import com.github.cadecode.ubp.starter.web.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GeneralException 统一处理器
 *
 * @author Cade Li
 * @date 2022/5/8
 */
@Slf4j
@RestControllerAdvice
public class GeneralExceptionAdvisor {

    /**
     * 处理 GeneralException
     */
    @ExceptionHandler(GeneralException.class)
    public ApiResult<Object> handleGeneralException(GeneralException e) {
        log.error("General Exception =>", e);
        // 特殊处理接口返回 null 的情况
        if (WebErrorEnum.RES_BODY_NULL.getCode().equals(e.getErrorCode().getCode())) {
            return ApiResult.ok(null);
        }
        return ApiResult.error(e.getErrorCode()).moreInfo(e.getMoreInfo());
    }

    /**
     * 兜底处理一般异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<Object> handleException(Exception e) {
        log.error("Exception =>", e);
        return ApiResult.error(ErrorCode.UNKNOWN).moreInfo(e.getMessage());
    }
}
