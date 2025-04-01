package com.github.cadecode.ubp.starter.cache.exception;

import com.github.cadecode.ubp.common.exception.BaseException;

/**
 * DLCache 异常
 *
 * @author Cade Li
 * @since 2023/6/17
 */
public class DLCacheException extends BaseException {
    public DLCacheException() {
    }

    public DLCacheException(String message, Object... params) {
        super(message, params);
    }

    public DLCacheException(String message, Throwable cause, Object... params) {
        super(message, cause, params);
    }

    public DLCacheException(Throwable cause) {
        super(cause);
    }

    public DLCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object... params) {
        super(message, cause, enableSuppression, writableStackTrace, params);
    }
}
