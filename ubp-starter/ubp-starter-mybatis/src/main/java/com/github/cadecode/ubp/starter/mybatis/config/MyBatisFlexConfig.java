package com.github.cadecode.ubp.starter.mybatis.config;

import com.github.cadecode.ubp.starter.mybatis.listener.BaseFieldOperationListener;
import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import com.mybatisflex.spring.boot.SqlSessionFactoryBeanCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-flex 配置类
 * <p>
 * 固定配置可在此配置类中配置，灵活配置推荐使用配置类
 *
 * @author Cade Li
 * @since 2024/4/24
 */
@Configuration
public class MyBatisFlexConfig {

    @ConditionalOnMissingBean
    @Bean
    public BaseFieldOperationListener baseInsertUpdateListener() {
        return new BaseFieldOperationListener();
    }

    /**
     * global-config 配置器示例
     * <p>
     * mybatis-flex 自身配置
     */
    @ConditionalOnMissingBean
    @Bean
    public MyBatisFlexCustomizer myBatisFlexCustomizer(BaseFieldOperationListener baseFieldOperationListener) {
        return globalConfig -> {
            globalConfig.registerInsertListener(baseFieldOperationListener, BaseFieldOperable.class);
            globalConfig.registerUpdateListener(baseFieldOperationListener, BaseFieldOperable.class);
        };
    }

    /**
     * config 配置器示例
     * <p>
     * mybatis 原生配置，可注册多个
     */
    // @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {};
    }

    /**
     * SqlSessionFactoryBean 配置器示例
     * <p>
     * SqlSessionFactory 配置，可注册多个
     */
    // @Bean
    public SqlSessionFactoryBeanCustomizer sqlSessionFactoryBeanCustomizer() {
        return factoryBean -> {};
    }
}
