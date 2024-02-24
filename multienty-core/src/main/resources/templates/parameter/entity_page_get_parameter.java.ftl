package ${cfg.package_parameter};

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import ${cfg.package_dto}.${entity}DTO;
<#list table.importPackages as pkg>
<#if pkg?index_of("com.baomidou.mybatisplus.annotation") == -1>
import ${pkg};
</#if>
</#list>
<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * ${table.comment}分页列表查询请求
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Data
<#if swagger>
@ApiModel(value = "${entity}PageGetParameter",description = "${table.comment}分页列表查询请求")
</#if>
@Accessors(chain = true)
public class ${entity}PageGetParameter extends DefaultListGetParameter<${entity}DTO>{

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#-- --------字段自动填充字段为空或者(填充为INSERT/INSERT_UPDATE)并且字段非主键--------- -->
        <#if field.keyFlag>
            <#if field.comment!?length gt 0>
                <#if swagger>
    /**
     * ${field.comment}
     */
     @ApiModelProperty(value = "${field.comment}列表")
                <#else>
    /**
     * ${field.comment}列表
     */
                </#if>
            </#if>
     private List<${field.propertyType}> ${field.propertyName}s;
        </#if>
        <#if cfg.sharding??>
            <#if cfg.sharding.shardingDatabase>
                <#if cfg.sharding.databaseShardingColumnName == field.propertyName>
    /**
     * ${field.comment}
     */
    @ApiModelProperty(value = "${field.comment}")
    @NotNull
    private ${field.propertyType} ${field.propertyName};
                </#if>
            </#if>
            <#if cfg.sharding.shardingTable>
                <#if cfg.sharding.tableShardingColumnName == field.propertyName>
    /**
     * ${field.comment}
     */
    @ApiModelProperty(value = "${field.comment}")
    @NotNull
    private ${field.propertyType} ${field.propertyName};
                </#if>
            </#if>
        </#if>
    </#list>
    <#------------  END 字段循环遍历  ---------->
}
