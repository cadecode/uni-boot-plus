package com.github.cadecode.ubp.starter.web.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分页参数
 *
 * @author Cade Li
 * @date 2023/4/23
 */
@Data
public class PageParams {

    @NotNull
    private Integer pageSize = 1;
    @NotNull
    private Integer pageNumber = 10;

    private Integer total;

    private String orderBy;
}
