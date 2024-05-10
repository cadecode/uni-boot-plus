package com.github.cadecode.ubp.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户组 转换类
 *
 * @author Cade Li
 * @since 2024/5/10
 */
@Mapper
public interface SysUserGroupConvert {

    SysUserGroupConvert INSTANCE = Mappers.getMapper(SysUserGroupConvert.class);

}
