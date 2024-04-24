package com.github.cadecode.ubp.framework.handler;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.github.cadecode.ubp.framework.bean.po.SysLog;
import com.github.cadecode.ubp.starter.log.annotation.ApiLogger;
import com.github.cadecode.ubp.starter.log.handler.AbstractApiLogHandler;
import com.github.cadecode.ubp.starter.log.model.BaseLogInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Log 处理器实现
 *
 * @author Cade Li
 * @since 2023/6/8
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ApiLogHandler extends AbstractApiLogHandler {

    /**
     * 构造 Log 信息对象
     */
    public SysLog generateLog(ProceedingJoinPoint point, BaseLogInfo baseLogInfo) {
        ApiLogger apiLogger = baseLogInfo.getApiLogger();
        return SysLog.builder()
                .logType(apiLogger.type())
                .url(baseLogInfo.getRequest().getRequestURL().toString())
                .exceptional(baseLogInfo.getExceptional())
                .description(apiLogger.description())
                .classMethod(point.getSignature().getDeclaringTypeName() + '.' + point.getSignature().getName())
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(JakartaServletUtil.getClientIP(baseLogInfo.getRequest()))
                .httpMethod(baseLogInfo.getRequest().getMethod())
                .result(baseLogInfo.getResultStr())
                .timeCost(baseLogInfo.getTimeCost())
                .build();
    }

    /**
     * 持久化日志
     */
    @Async
    public void save(ApiLogger apiLogger, Object o) {
        //
    }
}
