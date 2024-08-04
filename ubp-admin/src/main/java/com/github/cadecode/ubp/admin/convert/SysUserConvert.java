package com.github.cadecode.ubp.admin.convert;

import com.github.cadecode.ubp.admin.bean.entity.SysUser;
import com.github.cadecode.ubp.admin.bean.vo.SysUserLoginVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户 转换类
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "tokenInfo", ignore = true)
    SysUserLoginVo entityToLoginVo(SysUser entity);

}
