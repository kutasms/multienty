package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${cfg.package_dto}.${entity}DTO;
import ${cfg.package_parameter}.${entity}DetailGetParameter;
import ${cfg.package_parameter}.${entity}PageGetParameter;
import ${cfg.package_parameter}.${entity}DeleteParameter;
<#list table.fields as field>
    <#if field.propertyName == "status">
import ${cfg.package_parameter}.${entity}EnableParameter;
import ${cfg.package_parameter}.${entity}DisableParameter;
    </#if>
</#list>
import ${cfg.package_parameter}.${entity}SaveParameter;
import ${cfg.package_parameter}.${entity}UpdateParameter;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    <#list table.fields as field>
        <#if field.keyFlag>
    ${entity}DTO getDetail(${entity}DetailGetParameter parameter);

    void delete(${entity}DeleteParameter parameter);

    IPage<${entity}DTO> getPage(${entity}PageGetParameter parameter);
        </#if>
        <#if field.propertyName == "status">
    void enable(${entity}EnableParameter parameter);

    void disable(${entity}DisableParameter parameter);
        </#if>
    </#list>

    void save(${entity}SaveParameter parameter);

    void update(${entity}UpdateParameter parameter);

}
</#if>
