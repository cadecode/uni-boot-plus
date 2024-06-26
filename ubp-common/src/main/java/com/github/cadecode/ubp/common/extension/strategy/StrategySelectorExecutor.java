package com.github.cadecode.ubp.common.extension.strategy;

import cn.hutool.core.util.ObjUtil;
import com.github.cadecode.ubp.common.exception.ExtensionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.config.EnablePluginRegistries;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 策略执行器
 *
 * @author Cade Li
 * @since 2023/6/23
 */
@Slf4j
@RequiredArgsConstructor
// 启用 spring-plugin
@EnablePluginRegistries({StrategyService.class})
@Configuration
public class StrategySelectorExecutor extends AbstractStrategyExecutor {

    private final PluginRegistry<StrategyService, StrategyContext> pluginRegistry;


    /**
     * 执行匹配的第一个策略，不需要返回值
     *
     * @param clazz    StrategyService 实现类
     * @param context  扩展上下文
     * @param consumer consumer
     */
    @Override
    public <S extends StrategyService> void execute(Class<S> clazz, StrategyContext context, Consumer<S> consumer) {
        List<S> services = selectServices(clazz, context);
        if (ObjUtil.isNotEmpty(services)) {
            consumer.accept(services.get(0));
            return;
        }
        throw new ExtensionException("Strategy service not found, {}, {}", clazz, context.getStrategyType());
    }

    /**
     * 执行匹配的所以策略，不需要返回值
     *
     * @param clazz    StrategyService 实现类
     * @param context  扩展上下文
     * @param consumer consumer
     */
    @Override
    public <S extends StrategyService> void executeAll(Class<S> clazz, StrategyContext context, Consumer<S> consumer) {
        List<S> services = selectServices(clazz, context);
        if (ObjUtil.isNotEmpty(services)) {
            services.forEach(consumer);
            return;
        }
        throw new ExtensionException("Strategy service not found, {}, {}", clazz, context.getStrategyType());
    }

    /**
     * 执行匹配的第一个策略，需要返回值
     *
     * @param clazz    StrategyService 实现类
     * @param context  扩展上下文
     * @param function function
     * @return 返回值
     */
    @Override
    public <R, S extends StrategyService> R submit(Class<S> clazz, StrategyContext context, Function<S, R> function) {
        List<S> services = selectServices(clazz, context);
        if (ObjUtil.isNotEmpty(services)) {
            return function.apply(services.get(0));
        }
        throw new ExtensionException("Strategy service not found, {}, {}", clazz, context.getStrategyType());
    }

    /**
     * 执行匹配的所以策略，需要返回值
     *
     * @param clazz    StrategyService 实现类
     * @param context  扩展上下文
     * @param function function
     * @return 返回值
     */
    @Override
    public <R, S extends StrategyService> List<R> submitAll(Class<S> clazz, StrategyContext context, Function<S, R> function) {
        List<S> services = selectServices(clazz, context);
        if (ObjUtil.isNotEmpty(services)) {
            return services.stream()
                    .map(function)
                    .collect(Collectors.toList());
        }
        throw new ExtensionException("Strategy service not found, {}, {}", clazz, context.getStrategyType());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> List<S> selectServices(Class<S> clazz, StrategyContext context) {
        return (List<S>) pluginRegistry.getPluginsFor(context)
                .stream()
                .filter(o -> clazz.isAssignableFrom(o.getClass()))
                .collect(Collectors.toList());
    }
}
