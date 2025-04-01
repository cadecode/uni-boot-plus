package com.github.cadecode.ubp.common.extension.pipeline;

/**
 * 过滤器链接口
 *
 * @author Cade Li
 * @date 2023/6/20
 */
public interface PipelineFilterChain<T extends PipelineContext> {

    void filter(T context);

    void next(T context);
}
