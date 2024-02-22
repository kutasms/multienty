package ${cfg.package_parameter};

import lombok.Data;
import annotation.com.chia.multienty.core.LogMetaId;
<#list table.importPackages as pkg>
<#if pkg?index_of("com.baomidou.mybatisplus.annotation") == -1>
import ${pkg};
</#if>
import javax.validation.constraints.NotNull;
</#list>
<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.experimental.Accessors;
/**
 * <p>
 * ${table.comment}删除请求
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Data
<#if swagger>
@ApiModel(value = "${entity}DeleteParameter",description = "${table.comment}删除请求")
</#if>
@Accessors(chain = true)
public class ${entity}DeleteParameter {

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#-- --------字段自动填充字段为空或者(填充为INSERT/INSERT_UPDATE)并且字段非主键--------- -->
        <#if field.keyFlag>
            <#if field.comment!?length gt 0>
                <#if swagger>
    /**
     * ${field.comment}
     */
    @ApiModelProperty(value = "${field.comment}")
                <#else>
    /**
     * ${field.comment}
     */
                </#if>
            </#if>
    @LogMetaId
    private ${field.propertyType} ${field.propertyName};
        </#if>
        <#if cfg.sharding??>
            <#if cfg.sharding.shardingTable>
                <#if cfg.sharding.tableShardingColumnName == field.propertyName>
    /**
     * ${field.comment}
     */
    @ApiModelProperty(value = "${field.comment}")
    private ${field.propertyType} ${field.propertyName};
                </#if>
            </#if>
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
        </#if>
    </#list>

    <#------------  END 字段循环遍历  ---------->
}
