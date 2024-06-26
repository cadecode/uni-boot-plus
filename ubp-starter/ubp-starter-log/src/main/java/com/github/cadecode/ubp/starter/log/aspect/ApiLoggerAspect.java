package com.github.cadecode.ubp.starter.log.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjUtil;
import com.github.cadecode.ubp.common.util.JacksonUtil;
import com.github.cadecode.ubp.starter.log.annotation.ApiLogger;
import com.github.cadecode.ubp.starter.log.handler.BaseApiLogHandler;
import com.github.cadecode.ubp.starter.log.model.LogInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求响应信息日志 AOP 类
 *
 * @author Cade Li
 * @date 2021/12/4
 */
@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class ApiLoggerAspect {

    private final BaseApiLogHandler baseApiLogHandler;

    @Pointcut("@within(com.github.cadecode.ubp.starter.log.annotation.ApiLogger) " +
            "|| @annotation(com.github.cadecode.ubp.starter.log.annotation.ApiLogger)")
    public void pointCut() {

    }

    /**
     * 环绕通知
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 根据注解判断是否开启
        ApiLogger apiLogger = getApiLogger(point);
        if (!apiLogger.value()) {
            return point.proceed();
        }
        // 统计耗时
        long startTime = System.currentTimeMillis();
        Object result = null;
        Throwable throwable = null;
        try {
            result = point.proceed();
            return result;
        } catch (Throwable e) {
            throwable = e;
            throw e;
        } finally {
            handleLogger(point, apiLogger, result, throwable, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 获取 ApiLogger 注解
     */
    private ApiLogger getApiLogger(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        ApiLogger apiLogger = methodSignature.getMethod().getAnnotation(ApiLogger.class);
        if (apiLogger == null) {
            apiLogger = methodSignature.getMethod()
                    .getDeclaringClass()
                    .getAnnotation(ApiLogger.class);
        }
        return apiLogger;
    }

    /**
     * 获取请求、结果、异常等信息，构造日志
     */
    public void handleLogger(ProceedingJoinPoint point, ApiLogger apiLogger, Object result, Throwable throwable, Long timeCost) {
        // 解析请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjUtil.isNull(attributes)) {
            return;
        }
        try {
            String resultStr;
            boolean exceptional = false;
            if (ObjUtil.isNotNull(throwable)) {
                exceptional = true;
                resultStr = ExceptionUtil.stacktraceToString(throwable);
            } else {
                try {
                    resultStr = JacksonUtil.toJson(result);
                } catch (Exception e) {
                    resultStr = ExceptionUtil.stacktraceToString(e);
                    log.error("API LOG [{}]: request result to json fail", apiLogger.type(), e);
                }
            }
            LogInfo baseLogInfo = LogInfo.builder().apiLogger(apiLogger).request(attributes.getRequest())
                    .resultStr(resultStr).timeCost(timeCost).exceptional(exceptional).build();
            Object logObj = baseApiLogHandler.generateLog(point, baseLogInfo);
            // 打印日志
            log.info("API LOG [{}]: {}", apiLogger.type(), JacksonUtil.toJson(logObj));
            // 持久化
            try {
                baseApiLogHandler.save(apiLogger, logObj);
            } catch (Exception e) {
                log.error("API LOG [{}]: save async fail", apiLogger.type(), e);
            }
        } catch (Exception e) {
            log.error("API LOG [{}]: handle logger fail", apiLogger.type(), e);
        }
    }
}
