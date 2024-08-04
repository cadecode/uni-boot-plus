package com.github.cadecode.ubp.framework.bean.dto;

import com.github.cadecode.ubp.starter.web.model.PageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 系统日志分页 DTO
 *
 * @author Cade Li
 * @date 2024/8/4
 */
@Schema(description = "系统日志分页 DTO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLogPageDto extends PageParams {
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
