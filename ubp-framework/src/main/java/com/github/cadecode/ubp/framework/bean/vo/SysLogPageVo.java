package com.github.cadecode.ubp.framework.bean.vo;

import com.github.cadecode.ubp.starter.web.model.PageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 系统日志 VO 分页
 *
 * @author Cade Li
 * @since 2024/5/8
 */
public class SysLogPageVo {

    @Schema(description = "系统日志 VO 分页请求")
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SysLogPageRequestVo extends PageParams {
        @Schema(description = "开始时间")
        private Date startTime;
        @Schema(description = "接收时间")
        private Date endTime;
        @Schema(description = "log 类型列表")
        private List<String> logTypeList;
        @Schema(description = "URL")
        private String url;
        @Schema(description = "是否异常")
        private Boolean exceptional;
        @Schema(description = "访问者")
        private String accessUser;
    }

    @Data
    public static class SysLogPageResponseVo {
        @Schema(description = "ID")
        private BigInteger id;
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
}
