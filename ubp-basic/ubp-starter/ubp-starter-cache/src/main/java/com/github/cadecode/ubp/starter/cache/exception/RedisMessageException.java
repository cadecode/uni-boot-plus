package com.github.cadecode.ubp.starter.cache.exception;

import com.github.cadecode.ubp.common.exception.BaseException;

/**
 * Redis 过期处理异常
 *
 * @author Cade Li
 * @since 2023/6/13
 */
public class RedisMessageException extends BaseException {
    public RedisMessageException() {
    }

    public RedisMessageException(String message, Object... params) {
        super(message, params);
    }

    public RedisMessageException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public RedisMessageException(Throwable cause) {
        super(cause);
    }

    public RedisMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... params) {
        super(message, cause, enableSuppression, writableStackTrace, params);
    }
}
