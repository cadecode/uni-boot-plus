package com.github.cadecode.ubp.framework.bean.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统日志分页 VO
 *
 * @author Cade Li
 * @since 2024/5/8
 */
@Schema(description = "系统日志分页 VO")
@Data
public class SysLogPageVo {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "log 类型")
    private String logType;
    @Schema(description = "URL")
    private String url;
    @Schema(description = "是否异常")
    private Boolean exceptional;
    @Schema(description = "访问者")
    private String accessUser;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "方法名")
    private String classMethod;
    @Schema(description = "线程 ID")
    private String threadId;
    @Schema(description = "线程名")
    private String threadName;
    @Schema(description = "IP")
    private String ip;
    @Schema(description = "HTTP 方法")
    private String httpMethod;
    @Schema(description = "参数")
    private String requestParams;
    @Schema(description = "结果")
    private String result;
    @Schema(description = "接口耗时")
    private Long timeCost;
    @Schema(description = "操作系统")
    private String os;
    @Schema(description = "浏览器")
    private String browser;
    @Schema(description = "user-agent")
    private String userAgent;
    @Schema(description = "trace-id")
    private String traceId;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    @Schema(description = "更新人")
    private String updateUser;
}
