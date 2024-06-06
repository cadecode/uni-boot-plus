package com.github.cadecode.ubp.framework.controller;

import com.github.cadecode.ubp.framework.bean.po.SysLog;
import com.github.cadecode.ubp.framework.bean.vo.SysLogPageVo.SysLogPageReqVo;
import com.github.cadecode.ubp.framework.bean.vo.SysLogPageVo.SysLogPageRespVo;
import com.github.cadecode.ubp.framework.convert.SysLogConvert;
import com.github.cadecode.ubp.framework.service.SysLogService;
import com.github.cadecode.ubp.starter.web.annotation.ApiFormat;
import com.github.cadecode.ubp.starter.web.model.PageResult;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

import static com.github.cadecode.ubp.framework.bean.po.table.SysLogTableDef.SYS_LOG;

/**
 * 系统日志 控制层
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@ApiFormat
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "系统日志接口")
@RequestMapping("/sys_log")
public class SysLogController {

    private final SysLogService sysLogService;

    /**
     * 保存系统日志
     *
     * @param reqVo 系统日志 VO
     * @return 是否添加成功
     */
    @PostMapping("save")
    @Operation(summary = "保存系统日志")
    public Object save(@RequestBody @Valid @Parameter(description = "系统日志") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 根据主键删除系统日志
     *
     * @param id 系统日志主键
     * @return 是否删除成功
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键删除系统日志")
    public boolean remove(@PathVariable @Parameter(description = "系统日志主键") Serializable id) {
        return sysLogService.removeById(id);
    }

    /**
     * 根据主键删除系统日志
     *
     * @param idList 系统日志主键列表
     * @return 是否删除成功
     */
    @PostMapping("remove_by_ids")
    @Operation(summary = "根据主键系统日志-批量")
    public boolean removeByIds(@RequestBody @NotEmpty @Parameter(description = "系统日志主键") List<Serializable> idList) {
        return sysLogService.removeByIds(idList);
    }

    /**
     * 根据主键更新系统日志
     *
     * @param reqVo 系统日志 VO
     * @return 是否更新成功
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新系统日志")
    public boolean update(@RequestBody @Valid @Parameter(description = "系统日志主键") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求");
    }

    /**
     * 根据主键查询系统日志
     *
     * @param id 系统日志主键
     * @return 系统日志详情
     */
    @GetMapping("get/{id}")
    @Operation(summary = "根据主键查询系统日志")
    public Object get(@PathVariable @Parameter(description = "系统日志主键") Serializable id) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据主键查询系统日志
     *
     * @param idList 系统日志主键列表
     * @return 系统日志详情
     */
    @PostMapping("list_by_ids")
    @Operation(summary = "根据主键查询系统日志-批量")
    public List<Object> listByIds(@RequestBody @NotEmpty @Parameter(description = "系统日志主键") List<Serializable> idList) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据条件查询系统日志
     *
     * @param reqVo 系统日志 VO
     * @return 系统日志详情
     */
    @PostMapping("list")
    @Operation(summary = "根据条件查询系统日志")
    public List<Object> list(@RequestBody @NotEmpty @Parameter(description = "系统日志主键") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 分页查询系统日志
     *
     * @param reqVo 系统日志 VO
     * @return 分页结果
     */
    @PostMapping("page")
    @Operation(summary = "分页查询系统日志")
    public PageResult<SysLogPageRespVo> page(@RequestBody @Valid @Parameter(description = "分页信息") SysLogPageReqVo reqVo) {
        Page<SysLog> page = sysLogService.queryChain()
                .where(SYS_LOG.CREATE_TIME.between(reqVo.getStartTime(), reqVo.getEndTime()))
                .and(SYS_LOG.LOG_TYPE.in(reqVo.getLogTypeList()))
                .and(SYS_LOG.ACCESS_USER.likeLeft(reqVo.getAccessUser()))
                .and(SYS_LOG.URL.likeLeft(reqVo.getUrl()))
                .and(SYS_LOG.EXCEPTIONAL.eq(reqVo.getExceptional()))
                .orderBy(SYS_LOG.CREATE_TIME, false)
                .page(Page.of(reqVo.getPageNum(), reqVo.getPageSize()));
        List<SysLogPageRespVo> voList = SysLogConvert.INSTANCE.poToPageRespVo(page.getRecords());
        return new PageResult<>((int) page.getTotalRow(), voList);
    }

}
