package com.github.cadecode.ubp.admin.controller;

import com.github.cadecode.ubp.admin.service.SysRoleUserService;
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
 * 角色用户关系 控制层
 *
 * @author Cade Li
 * @since 2024/5/08
 */
@ApiFormat
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "角色用户关系接口")
@RequestMapping("/sys_role_user")
public class SysRoleUserController {

    private final SysRoleUserService sysRoleUserService;

    /**
     * 保存角色用户关系
     *
     * @param reqVo 角色用户关系 VO
     * @return 是否添加成功
     */
    @PostMapping("save")
    @Operation(summary = "保存角色用户关系")
    public Object save(@RequestBody @Valid @Parameter(description = "角色用户关系") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 根据主键删除角色用户关系
     *
     * @param id 角色用户关系主键
     * @return 是否删除成功
     */
    @DeleteMapping("remove/{id}")
    @Operation(summary = "根据主键删除角色用户关系")
    public boolean remove(@PathVariable @Parameter(description = "角色用户关系主键") Serializable id) {
        return sysRoleUserService.removeById(id);
    }

    /**
     * 根据主键删除角色用户关系
     *
     * @param idList 角色用户关系主键列表
     * @return 是否删除成功
     */
    @PostMapping("remove_by_ids")
    @Operation(summary = "根据主键角色用户关系-批量")
    public boolean removeByIds(@RequestBody @NotEmpty @Parameter(description = "角色用户关系主键") List<Serializable> idList) {
        return sysRoleUserService.removeByIds(idList);
    }

    /**
     * 根据主键更新角色用户关系
     *
     * @param reqVo 角色用户关系 VO
     * @return 是否更新成功
     */
    @PutMapping("update")
    @Operation(summary = "根据主键更新角色用户关系")
    public boolean update(@RequestBody @Valid @Parameter(description = "角色用户关系主键") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求");
    }

    /**
     * 根据主键查询角色用户关系
     *
     * @param id 角色用户关系主键
     * @return 角色用户关系详情
     */
    @GetMapping("get/{id}")
    @Operation(summary = "根据主键查询角色用户关系")
    public Object get(@PathVariable @Parameter(description = "角色用户关系主键") Serializable id) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据主键查询角色用户关系
     *
     * @param idList 角色用户关系主键列表
     * @return 角色用户关系详情
     */
    @PostMapping("list_by_ids")
    @Operation(summary = "根据主键查询角色用户关系-批量")
    public List<Object> listByIds(@RequestBody @NotEmpty @Parameter(description = "角色用户关系主键") List<Serializable> idList) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据条件查询角色用户关系
     *
     * @param reqVo 角色用户关系 VO
     * @return 角色用户关系详情
     */
    @PostMapping("list")
    @Operation(summary = "根据条件查询角色用户关系")
    public List<Object> list(@RequestBody @NotEmpty @Parameter(description = "角色用户关系主键") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 分页查询角色用户关系
     *
     * @param reqVo 角色用户关系 VO
     * @return 分页结果
     */
    @PostMapping("page")
    @Operation(summary = "分页查询角色用户关系")
    public PageResult<Object> page(@RequestBody @Valid @Parameter(description = "分页信息") Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

}
