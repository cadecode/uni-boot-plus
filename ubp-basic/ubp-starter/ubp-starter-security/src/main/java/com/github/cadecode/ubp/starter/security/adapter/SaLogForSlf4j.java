package com.github.cadecode.ubp.starter.security.adapter;

import cn.dev33.satoken.log.SaLog;
import lombok.extern.slf4j.Slf4j;

/**
 * 将 Sa-Token log 信息转接到 Slf4j
 *
 * @author Cade Li
 * @since 2024/5/6
 */
@Slf4j
public class SaLogForSlf4j implements SaLog {

    @Override
    public void trace(String str, Object... args) {
        log.trace(str, args);
    }

    @Override
    public void debug(String str, Object... args) {
        log.debug(str, args);
    }

    @Override
    public void info(String str, Object... args) {
        log.info(str, args);
    }

    @Override
    public void warn(String str, Object... args) {
        log.warn(str, args);
    }

    @Override
    public void error(String str, Object... args) {
        log.error(str, args);
    }

    @Override
    public void fatal(String str, Object... args) {
        log.error(str, args);
    }
}

