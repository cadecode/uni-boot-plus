package com.github.cadecode.ubp.framework.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统日志 转换类
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Mapper
public interface SysLogConvert {

    SysLogConvert INSTANCE = Mappers.getMapper(SysLogConvert.class);

}
