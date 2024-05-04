package com.github.cadecode.ubp.starter.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.cadecode.ubp.starter.cache.consts.CacheConst;
import com.github.cadecode.ubp.starter.cache.l2cache.DLCacheProperties;
import com.github.cadecode.ubp.starter.cache.l2cache.cache.DLCacheManager;
import com.github.cadecode.ubp.starter.cache.util.KeyGeneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * SpringBoot Cache 配置类
 *
 * @author Cade Li
 * @since 2022/1/5
 */
@RequiredArgsConstructor
@Configuration
@EnableCaching
@EnableConfigurationProperties(DLCacheProperties.class)
public class CacheConfig {

    /**
     * 二级缓存
     */
    @ConditionalOnMissingBean
    @Bean(name = CacheConst.CACHE_MANAGER_DL)
    public DLCacheManager dlCacheManager(DLCacheProperties cacheProperties, RedisTemplate<String, Object> redisTemplate) {
        return new DLCacheManager(cacheProperties, redisTemplate);
    }

    // @Bean(name = CacheConst.CACHE_MANAGER_CAFFEINE_5S)
    public CaffeineCacheManager caffeineCacheManager5s() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        // 过期时间设置为 5 s
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS));
        caffeineCacheManager.setAllowNullValues(true);
        return caffeineCacheManager;
    }

    // @Bean(name = CacheConst.CACHE_MANAGER_REDIS_5M)
    public RedisCacheManager redisCacheManager5m(RedisTemplate<String, Object> redisTemplate) {
        return geneRedisCacheManager(redisTemplate, 5);
    }

    // @Bean(name = CacheConst.CACHE_MANAGER_REDIS_30M)
    public RedisCacheManager redisCacheManager30m(RedisTemplate<String, Object> redisTemplate) {
        return geneRedisCacheManager(redisTemplate, 30);
    }

    private static RedisCacheManager geneRedisCacheManager(RedisTemplate<String, Object> redisTemplate, long minutes) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(o -> o + KeyGeneUtil.SEPARATOR)
                .serializeKeysWith(SerializationPair.fromSerializer(redisTemplate.getStringSerializer()))
                .serializeValuesWith(SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                .entryTtl(Duration.ofMinutes(minutes));
        return RedisCacheManager.builder(Objects.requireNonNull(redisTemplate.getConnectionFactory()))
                .cacheDefaults(cacheConfiguration)
                .transactionAware()
                .build();
    }
}
