package ${cfg.package_parameter};

import lombok.Data;
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
import lombok.experimental.Accessors;
import com.chia.multienty.core.annotation.LogMetaId;
/**
 * <p>
 * ${table.comment!}启用请求
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Data
@ApiModel(value = "${entity}EnableParameter",description = "${table.comment!}启用请求")
@Accessors(chain = true)
public class ${entity}EnableParameter {
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
