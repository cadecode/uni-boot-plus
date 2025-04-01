package com.github.cadecode.ubp.framework.starter.log;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.github.cadecode.ubp.common.util.JacksonUtil;
import com.github.cadecode.ubp.framework.bean.entity.SysLog;
import com.github.cadecode.ubp.framework.consts.HttpConst;
import com.github.cadecode.ubp.framework.service.SysLogService;
import com.github.cadecode.ubp.starter.log.annotation.ApiLogger;
import com.github.cadecode.ubp.starter.log.handler.BaseApiLogHandler;
import com.github.cadecode.ubp.starter.log.model.BaseLogInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
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
public class ApiLogHandler extends BaseApiLogHandler {

    private final SysLogService sysLogService;

    /**
     * 构造 Log 信息对象
     */
    public SysLog generateLog(ProceedingJoinPoint point, BaseLogInfo baseLogInfo) {
        ApiLogger apiLogger = baseLogInfo.getApiLogger();
        // 解析 user-agent
        String userAgentStr = JakartaServletUtil.getHeader(baseLogInfo.getRequest(), HttpConst.HEAD_USER_AGENT, CharsetUtil.CHARSET_UTF_8);
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        // 获取 trace-id
        String traceId = JakartaServletUtil.getHeader(baseLogInfo.getRequest(), HttpConst.HEAD_TRACE_ID, CharsetUtil.CHARSET_UTF_8);
        // 解析参数
        String paramsJson;
        try {
            paramsJson = JacksonUtil.toJson(getRequestParams(point, apiLogger));
        } catch (Exception e) {
            paramsJson = ExceptionUtil.stacktraceToString(e);
            log.error("API log [{}]: request params to json fail", apiLogger.type(), e);
        }
        // 获取描述
        String description = apiLogger.description();
        if (ObjUtil.isEmpty(description)) {
            Operation operation = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Operation.class);
            if (ObjUtil.isNotNull(operation)) {
                description = operation.description();
            }
        }
        return SysLog.builder()
                .logType(apiLogger.type())
                .url(baseLogInfo.getRequest().getRequestURL().toString())
                .exceptional(baseLogInfo.getExceptional())
                .accessUser(StpUtil.getLoginId(null))
                .description(description)
                .classMethod(point.getSignature().getDeclaringTypeName() + '.' + point.getSignature().getName())
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(JakartaServletUtil.getClientIP(baseLogInfo.getRequest()))
                .httpMethod(baseLogInfo.getRequest().getMethod())
                .requestParams(paramsJson)
                .result(baseLogInfo.getResultStr())
                .timeCost(baseLogInfo.getTimeCost())
                .userAgent(userAgentStr)
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOs().getName())
                .traceId(traceId)
                .build();
    }

    /**
     * 持久化日志
     */
    @Async
    public void save(ApiLogger apiLogger, Object o) {
        if (!apiLogger.enableSave()) {
            return;
        }
        SysLog syslog = (SysLog) o;
        if (!apiLogger.saveParams()) {
            syslog.setRequestParams(null);
        }
        if (!apiLogger.saveResult()) {
            syslog.setResult(null);
        }
        try {
            sysLogService.save(syslog);
        } catch (Exception e) {
            log.error("API log [{}]: save fail", apiLogger.type(), e);
        }
    }
}
