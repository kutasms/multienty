package ${cfg.package_parameter};

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
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
/**
 * <p>
 * ${table.comment}更新请求
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Data
<#if swagger>
@ApiModel(value = "${entity}UpdateParameter",description = "${table.comment}更新请求")
</#if>
@Accessors(chain = true)
public class ${entity}UpdateParameter {

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#-- --------字段自动填充字段为空或者(填充为INSERT/INSERT_UPDATE)并且字段非主键--------- -->
        <#if (!field.fill?? || ((field.fill != "UPDATE") && (field.fill != "INSERT_UPDATE")))>
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
            <#if field.keyFlag>
     @LogMetaId
            </#if>
            <#if cfg.sharding??>
                <#if cfg.sharding.shardingDatabase>
                    <#if cfg.sharding.databaseShardingColumnName == field.propertyName>
     @NotNull
                    </#if>
                </#if>
                <#if cfg.sharding.shardingTable>
                    <#if cfg.sharding.tableShardingColumnName == field.propertyName>
     @NotNull
                    </#if>
                </#if>
            </#if>
     private ${field.propertyType} ${field.propertyName};
        </#if>
    </#list>
    <#------------  END 字段循环遍历  ---------->
}
