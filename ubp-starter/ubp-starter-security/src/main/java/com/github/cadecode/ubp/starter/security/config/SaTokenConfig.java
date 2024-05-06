package com.github.cadecode.ubp.starter.security.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.log.SaLog;
import cn.dev33.satoken.stp.StpUtil;
import com.github.cadecode.ubp.starter.security.adapter.SaLogForSlf4j;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaToken 配置器
 *
 * @author Cade Li
 * @since 2024/5/5
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> {
                    StpUtil.checkLogin();
                }).isAnnotation(securityProperties.getEnableAnnotation()))
                .addPathPatterns("/**")
                .excludePathPatterns(securityProperties.getExcludePathPatterns());
        log.info("SaInterceptor enable annotation: {}", securityProperties.getEnableAnnotation());
        log.info("SaInterceptor exclude path patterns: {}", securityProperties.getExcludePathPatterns());
    }

    @Bean
    public SaLog saLog() {
        return new SaLogForSlf4j();
    }
}
