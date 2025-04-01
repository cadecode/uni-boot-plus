package com.github.cadecode.ubp.common.extension.pipeline;

import com.github.cadecode.ubp.common.enums.ExtensionType;
import com.github.cadecode.ubp.common.extension.pipeline.selector.FilterSelector;

/**
 * pipeline 上下文
 *
 * @author Cade Li
 * @since 2023/6/23
 */
public interface PipelineContext {

    ExtensionType getPipelineType();

    FilterSelector getFilterSelector();

    boolean continueChain();

}
