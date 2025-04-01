package com.github.cadecode.ubp.starter.log.config;

import com.github.cadecode.ubp.starter.log.handler.BaseApiLogHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ApiLog 配置
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Configuration
public class ApiLogConfig {

    @ConditionalOnMissingBean
    @Bean
    public BaseApiLogHandler baseApiLogHandler() {
        return new BaseApiLogHandler();
    }

}
