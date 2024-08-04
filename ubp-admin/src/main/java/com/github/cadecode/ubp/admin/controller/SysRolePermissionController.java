package com.github.cadecode.ubp.admin.controller;

import com.github.cadecode.ubp.admin.service.SysRolePermissionService;
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
 * 角色权限关系 控制层
 *
 * @author Cade Li
 * @since 2024/8/04
 */
@ApiFormat
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "角色权限关系接口")
@RequestMapping("/sys_role_permission")
public class SysRolePermissionController {

    private final SysRolePermissionService sysRolePermissionService;

    /**
     * 保存角色权限关系
     *
     * @param saveDto 角色权限关系 DTO
     * @return 是否添加成功
     */
    @PostMapping("save")
    @Operation(summary = "保存角色权限关系")
    public Object save(@RequestBody @Valid @Parameter(description = "角色权限关系") Object saveDto) {
        throw new RuntimeException("接口未完成！请使用合适的 DTO/VO 类表示请求和响应");
    }

    /**
     * 根据主键删除角色权限关系
     *
     * @param id 角色权限关系主键
     * @return 是否删除成功
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键删除角色权限关系")
    public boolean remove(@PathVariable @Parameter(description = "角色权限关系主键") Serializable id) {
        return sysRolePermissionService.removeById(id);
    }

    /**
     * 根据主键删除角色权限关系
     *
     * @param idList 角色权限关系主键列表
     * @return 是否删除成功
     */
    @PostMapping("remove_by_ids")
    @Operation(summary = "根据主键角色权限关系-批量")
    public boolean removeByIds(@RequestBody @NotEmpty @Parameter(description = "角色权限关系主键") List<Serializable> idList) {
        return sysRolePermissionService.removeByIds(idList);
    }

    /**
     * 根据主键更新角色权限关系
     *
     * @param updateDto 角色权限关系 DTO
     * @return 是否更新成功
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新角色权限关系")
    public boolean update(@RequestBody @Valid @Parameter(description = "角色权限关系主键") Object updateDto) {
        throw new RuntimeException("接口未完成！请使用合适的 DTO 类表示请求");
    }

    /**
     * 根据主键查询角色权限关系
     *
     * @param id 角色权限关系主键
     * @return 角色权限关系详情
     */
    @GetMapping("get/{id}")
    @Operation(summary = "根据主键查询角色权限关系")
    public Object get(@PathVariable @Parameter(description = "角色权限关系主键") Serializable id) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据主键查询角色权限关系
     *
     * @param idList 角色权限关系主键列表
     * @return 角色权限关系详情
     */
    @PostMapping("list_by_ids")
    @Operation(summary = "根据主键查询角色权限关系-批量")
    public List<Object> listByIds(@RequestBody @NotEmpty @Parameter(description = "角色权限关系主键") List<Serializable> idList) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据条件查询角色权限关系
     *
     * @param listDto 角色权限关系 DTO
     * @return 角色权限关系详情
     */
    @PostMapping("list")
    @Operation(summary = "根据条件查询角色权限关系")
    public List<Object> list(@RequestBody @NotEmpty @Parameter(description = "角色权限关系主键") Object listDto) {
        throw new RuntimeException("接口未完成！请使用合适的 DTO/VO 类表示请求和响应");
    }

    /**
     * 分页查询角色权限关系
     *
     * @param pageDto 角色权限关系 DTO
     * @return 分页结果
     */
    @PostMapping("page")
    @Operation(summary = "分页查询角色权限关系")
    public PageResult<Object> page(@RequestBody @Valid @Parameter(description = "分页信息") Object pageDto) {
        throw new RuntimeException("接口未完成！请使用合适的 DTO/VO 类表示请求和响应");
    }

}
