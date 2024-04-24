package com.github.cadecode.ubp.starter.log.model;

import com.github.cadecode.ubp.starter.log.annotation.ApiLogger;
import jakarta.servlet.http.HttpServletRequest;

/**
 * api log 信息
 *
 * @author Cade Li
 * @since 2023/8/13
 */
public interface BaseLogInfo {
    ApiLogger getApiLogger();

    Boolean getExceptional();

    HttpServletRequest getRequest();

    String getResultStr();

    Long getTimeCost();
}
