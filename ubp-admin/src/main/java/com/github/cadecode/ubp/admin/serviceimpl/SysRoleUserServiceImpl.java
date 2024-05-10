package com.github.cadecode.ubp.admin.serviceimpl;

import com.github.cadecode.ubp.admin.bean.po.SysRoleUser;
import com.github.cadecode.ubp.admin.mapper.SysRoleUserMapper;
import com.github.cadecode.ubp.admin.service.SysRoleUserService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色用户关系 服务层实现
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

}
