package com.github.cadecode.ubp.framework.bean.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统日志
 *
 * @author Cade Li
 * @since 2023/5/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysLog {

    private Long id;

    private String logType;
    /**
     * url
     */
    private String url;
    /**
     * 是否异常
     */
    private Boolean exceptional;
    /**
     * 访问者
     */
    private String accessUser;
    /**
     * 描述
     */
    private String description;
    /**
     * 类方法
     */
    private String classMethod;
    /**
     * 线程id
     */
    private String threadId;
    /**
     * 线程名称
     */
    private String threadName;
    /**
     * ip
     */
    private String ip;
    /**
     * http 方法
     */
    private String httpMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 返回结果
     */
    private String result;
    /**
     * 接口耗时
     */
    private Long timeCost;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * user-agent
     */
    private String userAgent;

    private String traceId;

    private Date createTime;

    private Date updateTime;

    private String updateUser;
}
