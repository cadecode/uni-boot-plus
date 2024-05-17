package com.github.cadecode.ubp.starter.security.config;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.github.cadecode.ubp.starter.security.encrypt.PasswordEncryptor;
import com.github.cadecode.ubp.starter.security.filter.CorsAllowAnyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

/**
 * security 配置
 *
 * @author Cade Li
 * @since 2024/5/6
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    @ConditionalOnProperty(name = "uni-boot.security.enable-cors")
    @Bean
    public CorsFilter corsFilter() {
        log.info("Init CorsFilter");
        return new CorsAllowAnyFilter();
    }

    @ConditionalOnMissingBean
    @Bean
    public PasswordEncryptor sha256PasswordEncryptor() {
        return (password, secret) -> SaSecureUtil.sha256(password);
    }
}
