package com.github.cadecode.ubp.starter.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * security 配置项
 *
 * @author Cade Li
 * @since 2024/5/6
 */
@Data
@ConfigurationProperties(prefix = "uni-boot.security")
public class SecurityProperties {

    /**
     * 是否启用注解校验
     */
    private Boolean enableAnnotation = true;

    /**
     * 拦截器排除路径
     */
    private List<String> excludePathPatterns = new ArrayList<>();

    /**
     * 是否允许跨域
     */
    private Boolean enableCors = true;
}
