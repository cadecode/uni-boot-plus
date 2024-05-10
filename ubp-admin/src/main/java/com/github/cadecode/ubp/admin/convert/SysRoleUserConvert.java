package com.github.cadecode.ubp.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色用户关系 转换类
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Mapper
public interface SysRoleUserConvert {

    SysRoleUserConvert INSTANCE = Mappers.getMapper(SysRoleUserConvert.class);

}
