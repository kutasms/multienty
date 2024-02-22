package ${cfg.package_parameter};

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import annotation.com.chia.multienty.core.LogMetaId;
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
 * ${table.comment}保存请求
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Data
<#if swagger>
@ApiModel(value = "${entity}SaveParameter",description = "${table.comment}保存请求")
</#if>
@Accessors(chain = true)
public class ${entity}SaveParameter {

    <#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
        <#-- --------字段自动填充字段为空或者(填充为INSERT/INSERT_UPDATE)并且字段非主键--------- -->
        <#if (!field.fill?? || ((field.fill != "INSERT") && (field.fill != "INSERT_UPDATE")))>
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
    @JsonIgnore
    @JsonProperty(required = false)
            </#if>
        <#if cfg.sharding??>
            <#if cfg.sharding.shardingTable>
                <#if cfg.sharding.tableShardingColumnName == field.propertyName>
    @NotNull
                </#if>
            </#if>
            <#if cfg.sharding.shardingDatabase>
                <#if cfg.sharding.databaseShardingColumnName == field.propertyName>
    @NotNull
                </#if>
            </#if>
        </#if>
    private ${field.propertyType} ${field.propertyName};
        </#if>
    </#list>
    <#------------  END 字段循环遍历  ---------->
}
