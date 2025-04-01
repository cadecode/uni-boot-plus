package com.github.cadecode.ubp.starter.cache.exception;

import com.github.cadecode.ubp.common.exception.BaseException;

/**
 * Redis 相关异常
 *
 * @author Cade Li
 * @since 2023/6/9
 */
public class RedisLockException extends BaseException {
    public RedisLockException() {
    }

    public RedisLockException(String message, Object... params) {
        super(message, params);
    }

    public RedisLockException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public RedisLockException(Throwable cause) {
        super(cause);
    }

    public RedisLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... params) {
        super(message, cause, enableSuppression, writableStackTrace, params);
    }
}
