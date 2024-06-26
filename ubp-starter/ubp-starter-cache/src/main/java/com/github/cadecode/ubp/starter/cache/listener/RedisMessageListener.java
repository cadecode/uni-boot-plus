package com.github.cadecode.ubp.starter.cache.listener;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.Topic;

import java.util.List;

/**
 * Redis 消息监听器父类
 * <p>添加 topics 方法方便注册到 RedisMessageListenerContainer
 *
 * @author Cade Li
 * @since 2023/6/14
 */
public abstract class RedisMessageListener implements MessageListener {

    public abstract List<Topic> topics();
}
