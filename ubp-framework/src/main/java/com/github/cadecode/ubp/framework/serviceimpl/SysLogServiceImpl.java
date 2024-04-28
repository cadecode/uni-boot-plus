package com.github.cadecode.ubp.framework.serviceimpl;

import com.github.cadecode.ubp.framework.bean.po.SysLog;
import com.github.cadecode.ubp.framework.mapper.SysLogMapper;
import com.github.cadecode.ubp.framework.service.SysLogService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统日志 服务层实现
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
