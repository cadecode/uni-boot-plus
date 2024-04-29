#set(tableComment = table.getComment())
#set(entityClassName = table.buildEntityClassName())
#set(entityVarName = firstCharToLowerCase(entityClassName))
#set(serviceVarName = firstCharToLowerCase(table.buildServiceClassName()))
package #(packageConfig.controllerPackage);

#if(controllerConfig.superClass != null)
import #(controllerConfig.buildSuperClassImport());
#end
import #(packageConfig.servicePackage).#(table.buildServiceClassName());
import com.github.cadecode.ubp.starter.web.annotation.ApiFormat;
import com.github.cadecode.ubp.starter.web.model.PageResult;
#if(withSwagger && swaggerVersion.getName() == "FOX")
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
#end
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
#if(controllerConfig.restStyle)
import org.springframework.web.bind.annotation.RestController;
#else
import org.springframework.stereotype.Controller;
#end

import java.io.Serializable;
import java.util.List;

/**
 * #(tableComment) 控制层
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
@ApiFormat
@RequiredArgsConstructor
@Validated
#if(controllerConfig.restStyle)
@RestController
#else
@Controller
#end
#if(withSwagger && swaggerVersion.getName() == "FOX")
@Api("#(tableComment)接口")
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
@Tag(name = "#(tableComment)接口")
#end
@RequestMapping("/#(camelToUnderline(entityClassName))")
public class #(table.buildControllerClassName()) #if(controllerConfig.superClass)extends #(controllerConfig.buildSuperClassName()) #end {

    private final #(table.buildServiceClassName()) #(serviceVarName);

    /**
     * 保存#(tableComment)
     *
     * @param reqVo #(tableComment) VO
     * @return 是否添加成功
     */
    @PostMapping("save")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("保存#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "保存#(tableComment)")
    #end
    public Object save(@RequestBody @Valid #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)")#end  Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 根据主键删除#(tableComment)
     *
     * @param id #(tableComment)主键
     * @return 是否删除成功
     */
    @DeleteMapping("remove/{id}")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("根据主键删除#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "根据主键删除#(tableComment)")
    #end
    public boolean remove(@PathVariable #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)主键")#end  Serializable id) {
        return #(serviceVarName).removeById(id);
    }

    /**
     * 根据主键删除#(tableComment)
     *
     * @param idList #(tableComment)主键列表
     * @return 是否删除成功
     */
    @PostMapping("remove_by_ids")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("根据主键#(tableComment)-批量")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "根据主键#(tableComment)-批量")
    #end
    public boolean removeByIds(@RequestBody @NotEmpty #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)主键")#end  List<Serializable> idList) {
        return #(serviceVarName).removeByIds(idList);
    }

    /**
     * 根据主键更新#(tableComment)
     *
     * @param reqVo #(tableComment) VO
     * @return 是否更新成功
     */
    @PutMapping("update")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("根据主键更新#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "根据主键更新#(tableComment)")
    #end
    public boolean update(@RequestBody @Valid #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)主键")#end  Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求");
    }

    /**
     * 根据主键查询#(tableComment)
     *
     * @param id #(tableComment)主键
     * @return #(tableComment)详情
     */
    @GetMapping("get/{id}")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("根据主键查询#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "根据主键查询#(tableComment)")
    #end
    public Object get(@PathVariable #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)主键")#end  Serializable id) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据主键查询#(tableComment)
     *
     * @param idList #(tableComment)主键列表
     * @return #(tableComment)详情
     */
    @PostMapping("list_by_ids")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("根据主键查询#(tableComment)-批量")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "根据主键查询#(tableComment)-批量")
    #end
    public List<Object> listByIds(@RequestBody @NotEmpty #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)主键")#end  List<Serializable> idList) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示响应");
    }

    /**
     * 根据条件查询#(tableComment)
     *
     * @param reqVo #(tableComment) VO
     * @return #(tableComment)详情
     */
    @PostMapping("list")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("根据条件查询#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "根据条件查询#(tableComment)")
    #end
    public List<Object> list(@RequestBody @NotEmpty #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "#(tableComment)主键")#end  Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

    /**
     * 分页查询#(tableComment)
     *
     * @param reqVo #(tableComment) VO
     * @return 分页结果
     */
    @PostMapping("page")
    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("分页查询#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description = "分页查询#(tableComment)")
    #end
    public PageResult<Object> page(@RequestBody @Valid #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("分页信息") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description = "分页信息")#end  Object reqVo) {
        throw new RuntimeException("接口未完成！请使用合适的 VO 类表示请求和响应");
    }

}
