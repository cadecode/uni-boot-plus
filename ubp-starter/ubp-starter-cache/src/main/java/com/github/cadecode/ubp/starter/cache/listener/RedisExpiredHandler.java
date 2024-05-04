package com.github.cadecode.ubp.starter.cache.listener;

/**
 * Redis 过期时间处理器接口
 *
 * @author Cade Li
 * @since 2023/6/12
 */
public interface RedisExpiredHandler {

    boolean checkKey(String key);

    void handle(String key);
}
