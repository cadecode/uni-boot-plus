package com.github.cadecode.ubp.admin.controller;

import com.github.cadecode.ubp.admin.service.SysUserGroupService;
import com.github.cadecode.ubp.starter.web.annotation.ApiFormat;
import com.github.cadecode.ubp.starter.web.model.PageResult;
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

/**
 * 系统用户组 控制层
 *
 * @author Cade Li
 * @since 2024/5/10
 */
@ApiFormat
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "系统用户组接口")
@RequestMapping("/sys_user_group")
public class SysUserGroupController {

    private final SysUserGroupService sysUserGroupService;

    /**
     * 保存系统用户组
     *
     * @param reqVo 系统用户组 VO
     * @return 是否添加成功
     */
    @PostMapping("save")
    @Operation(description = "保存系统用户组")
    public Object save(@RequestBody @Valid @Parameter(description = "系统用户组") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 根据主键删除系统用户组
     *
     * @param id 系统用户组主键
     * @return 是否删除成功
     */
    @DeleteMapping("remove/{id}")
    @Operation(description = "根据主键删除系统用户组")
    public boolean remove(@PathVariable @Parameter(description = "系统用户组主键") Serializable id) {
        return sysUserGroupService.removeById(id);
    }

    /**
     * 根据主键删除系统用户组
     *
     * @param idList 系统用户组主键列表
     * @return 是否删除成功
     */
    @PostMapping("remove_by_ids")
    @Operation(description = "根据主键系统用户组-批量")
    public boolean removeByIds(@RequestBody @NotEmpty @Parameter(description = "系统用户组主键") List<Serializable> idList) {
        return sysUserGroupService.removeByIds(idList);
    }

    /**
     * 根据主键更新系统用户组
     *
     * @param reqVo 系统用户组 VO
     * @return 是否更新成功
     */
    @PutMapping("update")
    @Operation(description = "根据主键更新系统用户组")
    public boolean update(@RequestBody @Valid @Parameter(description = "系统用户组主键") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求");
    }

    /**
     * 根据主键查询系统用户组
     *
     * @param id 系统用户组主键
     * @return 系统用户组详情
     */
    @GetMapping("get/{id}")
    @Operation(description = "根据主键查询系统用户组")
    public Object get(@PathVariable @Parameter(description = "系统用户组主键") Serializable id) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据主键查询系统用户组
     *
     * @param idList 系统用户组主键列表
     * @return 系统用户组详情
     */
    @PostMapping("list_by_ids")
    @Operation(description = "根据主键查询系统用户组-批量")
    public List<Object> listByIds(@RequestBody @NotEmpty @Parameter(description = "系统用户组主键") List<Serializable> idList) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据条件查询系统用户组
     *
     * @param reqVo 系统用户组 VO
     * @return 系统用户组详情
     */
    @PostMapping("list")
    @Operation(description = "根据条件查询系统用户组")
    public List<Object> list(@RequestBody @NotEmpty @Parameter(description = "系统用户组主键") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 分页查询系统用户组
     *
     * @param reqVo 系统用户组 VO
     * @return 分页结果
     */
    @PostMapping("page")
    @Operation(description = "分页查询系统用户组")
    public PageResult<Object> page(@RequestBody @Valid @Parameter(description = "分页信息") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

}
