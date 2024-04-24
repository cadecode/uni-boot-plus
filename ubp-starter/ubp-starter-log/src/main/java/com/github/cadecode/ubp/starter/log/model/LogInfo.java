package com.github.cadecode.ubp.starter.log.model;

import com.github.cadecode.ubp.starter.log.annotation.ApiLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api log 信息
 *
 * @author Cade Li
 * @since 2023/8/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInfo implements BaseLogInfo {
    private ApiLogger apiLogger;
    private Boolean exceptional;
    private HttpServletRequest request;
    private String resultStr;
    private Long timeCost;
}
