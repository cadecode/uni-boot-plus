package com.github.cadecode.ubp.starter.cache.config;

import com.github.cadecode.ubp.starter.cache.l2cache.DLCacheProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置项
 *
 * @author Cade Li
 * @since 2024/5/4
 */
@Data
@ConfigurationProperties(prefix = "uni-boot.cache")
public class CacheProperties {

    /**
     * cache manager 类型
     * @see com.github.cadecode.ubp.starter.cache.consts.CacheManageConst
     * @see CacheManagerAutoConfig
     */
    private String type;

    private DLCacheProperties dlCache = new DLCacheProperties();
}
