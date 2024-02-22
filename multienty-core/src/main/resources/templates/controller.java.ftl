package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Service}.${table.serviceName};
import ${cfg.package_dto}.${entity}DTO;
import ${package.Entity}.${entity};
import ${cfg.package_parameter}.${entity}DetailGetParameter;
import ${cfg.package_parameter}.${entity}PageGetParameter;
import ${cfg.package_parameter}.${entity}DeleteParameter;
import ${cfg.package_parameter}.${entity}SaveParameter;
import ${cfg.package_parameter}.${entity}UpdateParameter;
<#list table.fields as field>
    <#if field.propertyName == "status">
import ${cfg.package_parameter}.${entity}EnableParameter;
import ${cfg.package_parameter}.${entity}DisableParameter;
    </#if>
</#list>
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import annotation.com.chia.multienty.core.WebLog;
import basic.domain.com.chia.multienty.core.Result;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import lombok.RequiredArgsConstructor;
<#if swagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
/**
 * <p>
 * ${table.comment!} 服务
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Validated
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@RequiredArgsConstructor
<#if swagger>
@Api(tags = "${table.comment!}")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    <#assign serviceName = table.serviceName?uncap_first>
    private final ${table.serviceName} ${serviceName};

    @PostMapping("/detail")
    <#if swagger>
    @ApiOperation("获取${table.comment!}详情")
    </#if>
    public Result<${entity}DTO> getDetail(@Validated @RequestBody ${entity}DetailGetParameter parameter) {
        ${entity}DTO detail = ${serviceName}.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    <#if swagger>
    @ApiOperation("获取${table.comment!}分页列表")
    </#if>
    public Result<IPage<${entity}DTO>> getPage(@Validated @RequestBody ${entity}PageGetParameter parameter) {
        IPage<${entity}DTO> page = ${serviceName}.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    <#if swagger>
    @ApiOperation("更新${table.comment!}")
    </#if>
    @WebLog
    public Result<Boolean> update(@Validated @RequestBody ${entity}UpdateParameter parameter) {
        ${serviceName}.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    <#if swagger>
    @ApiOperation("保存${table.comment!}")
    </#if>
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody ${entity}SaveParameter parameter) {
        ${serviceName}.save(parameter);
        return new Result<>(true);
    }

<#list table.fields as field>
    <#if field.propertyName == "status">
    @PostMapping("/enable")
    <#if swagger>
    @ApiOperation("启用${table.comment!}")
    </#if>
    @WebLog
    public Result<Boolean> enable(@Validated @RequestBody ${entity}EnableParameter parameter) {
        ${serviceName}.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    <#if swagger>
    @ApiOperation("禁用${table.comment!}")
    </#if>
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody ${entity}DisableParameter parameter) {
        ${serviceName}.disable(parameter);
        return new Result<>(true);
    }
    </#if>
</#list>

    @DeleteMapping("/delete")
    <#if swagger>
    @ApiOperation("删除${table.comment!}")
    </#if>
    @WebLog
    public Result<Boolean> delete(@Validated @RequestBody ${entity}DeleteParameter parameter) {
        ${serviceName}.delete(parameter);
        return new Result<>(true);
    }

}
</#if>
