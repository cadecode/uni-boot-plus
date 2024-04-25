package com.github.cadecode.ubp.starter.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * PageHelper 配置
 *
 * @author Cade Li
 * @since 2024/4/25
 */
@Configuration
public class PageHelperConfig {

    @ConditionalOnMissingBean
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        // 分页配置
        Properties properties = new Properties();
        // pageNum<=0 查第一页
        properties.setProperty("reasonable", "true");
        // pageSize=0 查全部
        properties.setProperty("page-size-zero", "true");
        // 自动识别方言
        properties.setProperty("autoRuntimeDialect", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}
