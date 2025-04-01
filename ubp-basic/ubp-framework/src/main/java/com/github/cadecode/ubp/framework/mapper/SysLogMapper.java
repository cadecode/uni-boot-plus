package com.github.cadecode.ubp.framework.mapper;

import com.github.cadecode.ubp.framework.bean.entity.SysLog;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志 映射层
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
