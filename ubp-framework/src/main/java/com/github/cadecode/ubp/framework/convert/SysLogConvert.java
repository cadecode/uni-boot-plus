package com.github.cadecode.ubp.framework.convert;

import com.github.cadecode.ubp.framework.bean.po.SysLog;
import com.github.cadecode.ubp.framework.bean.vo.SysLogPageVo.SysLogPageRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统日志 转换类
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Mapper
public interface SysLogConvert {

    SysLogConvert INSTANCE = Mappers.getMapper(SysLogConvert.class);

    SysLogPageRespVo poToPageRespVo(SysLog po);

    List<SysLogPageRespVo> poToPageRespVo(List<SysLog> poList);
}
