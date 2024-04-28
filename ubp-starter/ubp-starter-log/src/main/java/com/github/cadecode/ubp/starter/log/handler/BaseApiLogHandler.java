package com.github.cadecode.ubp.starter.log.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.github.cadecode.ubp.starter.log.annotation.ApiLogger;
import com.github.cadecode.ubp.starter.log.model.BaseLogInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Api Log 处理器抽象
 *
 * @author Cade Li
 * @since 2023/6/8
 */
@Slf4j
public class BaseApiLogHandler {

    public Object generateLog(ProceedingJoinPoint point, BaseLogInfo baseLogInfo) {
        return baseLogInfo;
    }

    public void save(ApiLogger apiLogger, Object o) {
        log.debug("API LOG [{}]: no implement for log save", apiLogger.type());
    }

    /**
     * 获取方法参数名和参数值
     */
    public static Map<String, Object> getRequestParams(JoinPoint joinPoint, ApiLogger apiLogger) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] names = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        if (ObjUtil.isEmpty(names) || ObjUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.error("API LOG [{}]: method [{}] param and the pass value do not match", apiLogger.type(), methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> paramMap = new HashMap<>();
        HashSet<String> ignoreFieldSet = CollUtil.newHashSet(apiLogger.ignoreFields());
        for (int i = 0; i < names.length; i++) {
            // 对敏感字段进行排除
            if (ignoreFieldSet.contains(names[i])) {
                paramMap.put(names[i], null);
                continue;
            }
            // 排除请求对象和响应
            if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse) {
                continue;
            }
            // 处理 MultipartFile
            if (args[i] instanceof MultipartFile) {
                paramMap.put(names[i], ((MultipartFile) args[i]).getOriginalFilename());
                continue;
            }
            paramMap.put(names[i], args[i]);
        }
        return paramMap;
    }
}
