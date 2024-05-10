package com.github.cadecode.ubp.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统角色 转换类
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Mapper
public interface SysRoleConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

}
