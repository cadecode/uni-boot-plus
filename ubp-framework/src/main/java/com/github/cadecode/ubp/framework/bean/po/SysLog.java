package com.github.cadecode.ubp.framework.bean.po;

import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志 实体类
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "系统日志")
@Table("sys_log")
public class SysLog implements BaseFieldOperable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Schema(description = "ID")
    private Long id;

    /**
     * log 类型
     */
    @Schema(description = "log 类型")
    private String logType;

    /**
     * URL
     */
    @Schema(description = "URL")
    private String url;

    /**
     * 是否异常
     */
    @Schema(description = "是否异常")
    private Boolean exceptional;

    /**
     * 访问者
     */
    @Schema(description = "访问者")
    private String accessUser;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 方法名
     */
    @Schema(description = "方法名")
    private String classMethod;

    /**
     * 线程 ID
     */
    @Schema(description = "线程 ID")
    private String threadId;

    /**
     * 线程名
     */
    @Schema(description = "线程名")
    private String threadName;

    /**
     * IP
     */
    @Schema(description = "IP")
    private String ip;

    /**
     * HTTP 方法
     */
    @Schema(description = "HTTP 方法")
    private String httpMethod;

    /**
     * 参数
     */
    @Schema(description = "参数")
    private String requestParams;

    /**
     * 结果
     */
    @Schema(description = "结果")
    private String result;

    /**
     * 接口耗时
     */
    @Schema(description = "接口耗时")
    private Long timeCost;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String os;

    /**
     * 浏览器
     */
    @Schema(description = "浏览器")
    private String browser;

    /**
     * user-agent
     */
    @Schema(description = "user-agent")
    private String userAgent;

    /**
     * trace-id
     */
    @Schema(description = "trace-id")
    private String traceId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateUser;

}
