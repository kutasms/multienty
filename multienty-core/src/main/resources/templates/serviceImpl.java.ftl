package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${cfg.package_dto}.${entity}DTO;
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
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
<#if cfg.sharding??>
import com.chia.multienty.core.tools.MultientyContext;
import com.baomidou.dynamic.datasource.annotation.DS;
</#if>
<#if idType ??>
import com.chia.multienty.core.tools.IdWorkerProvider;
</#if>
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@RequiredArgsConstructor
<#if cfg.sharding??>
@DS(MultientyConstants.DS_SHARDING)
</#if>
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    <#list table.fields as field>
        <#if field.keyFlag>

    @Override
    public ${entity}DTO getDetail(${entity}DetailGetParameter parameter) {
            <#if cfg.sharding??>
        return selectJoinOne(${entity}DTO.class,
                        MPJWrappers.<${entity}>lambdaJoin()
                <#if cfg.sharding.shardingDatabase>
                        .eq(${entity}::get${cfg.sharding.databaseShardingColumnName?cap_first}, parameter.get${cfg.sharding.databaseShardingColumnName?cap_first}())
                </#if>
                <#if cfg.sharding.shardingTable>
                        .eq(${entity}::get${cfg.sharding.tableShardingColumnName?cap_first}, parameter.get${cfg.sharding.tableShardingColumnName?cap_first}())
                </#if>
                        .eq(${entity}::get${field.propertyName?cap_first}, parameter.get${field.propertyName?cap_first}()));
            <#else>
        return selectJoinOne(${entity}DTO.class,
                        MPJWrappers.<${entity}>lambdaJoin().eq(${entity}::get${field.propertyName?cap_first}, parameter.get${field.propertyName?cap_first}()));
            </#if>
    }

    @Override
    public void update(${entity}UpdateParameter parameter) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        BeanUtils.copyProperties(parameter, ${entity?uncap_first});
                <#if cfg.sharding??>
        update(${entity?uncap_first}, new LambdaQueryWrapper<${entity}>()
                    <#if cfg.sharding.shardingDatabase>
                .eq(${entity}::get${cfg.sharding.databaseShardingColumnName?cap_first}, parameter.get${cfg.sharding.databaseShardingColumnName?cap_first}())
                    </#if>
                    <#if cfg.sharding.shardingTable>
                .eq(${entity}::get${cfg.sharding.tableShardingColumnName?cap_first}, parameter.get${cfg.sharding.tableShardingColumnName?cap_first}())
                    </#if>
                .eq(${entity}::get${field.propertyName?cap_first}, parameter.get${field.propertyName?cap_first}()));
                <#else>
        updateByIdTE(${entity?uncap_first});
                </#if>
    }

    @Override
    public void delete(${entity}DeleteParameter parameter) {
            <#if cfg.sharding??>
        removeTE(new LambdaQueryWrapper<${entity}>()
                <#if cfg.sharding.shardingDatabase>
                .eq(${entity}::get${cfg.sharding.databaseShardingColumnName?cap_first}, parameter.get${cfg.sharding.databaseShardingColumnName?cap_first}())
                </#if>
                <#if cfg.sharding.shardingTable>
                .eq(${entity}::get${cfg.sharding.tableShardingColumnName?cap_first}, parameter.get${cfg.sharding.tableShardingColumnName?cap_first}())
                </#if>
                .eq(${entity}::get${field.propertyName?cap_first}, parameter.get${field.propertyName?cap_first}()));
            <#else>
        removeByIdTE(parameter.get${field.propertyName?cap_first}());
            </#if>
    }

    @Override
    public IPage<${entity}DTO> getPage(${entity}PageGetParameter parameter) {
            <#if cfg.sharding??>
        return selectJoinListPage(parameter.getPageObj(), ${entity}DTO.class,
                new MTLambdaWrapper<${entity}>()
                        .solveGenericParameters(parameter)
                <#if cfg.sharding.shardingDatabase>
                        .eq(${entity}::get${cfg.sharding.databaseShardingColumnName?cap_first}, parameter.get${cfg.sharding.databaseShardingColumnName?cap_first}())
                </#if>
                <#if cfg.sharding.shardingTable && cfg.sharding.tableShardingColumnName != "createTime">
                        .eq(${entity}::get${cfg.sharding.tableShardingColumnName?cap_first}, parameter.get${cfg.sharding.tableShardingColumnName?cap_first}())
                </#if>
                        .in(!ListUtil.isEmpty(parameter.get${field.propertyName?cap_first}s()),
                                ${entity}::get${field.propertyName?cap_first},
                                parameter.get${field.propertyName?cap_first}s())
        );
            <#else>
        return selectJoinListPage(parameter.getPageObj(), ${entity}DTO.class,
                new MTLambdaWrapper<${entity}>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.get${field.propertyName?cap_first}s()),
                                ${entity}::get${field.propertyName?cap_first},
                                parameter.get${field.propertyName?cap_first}s())
        );
            </#if>
    }
        </#if>
    </#list>

    @Override
    public void save(${entity}SaveParameter parameter) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        BeanUtils.copyProperties(parameter, ${entity?uncap_first});
        <#list table.fields as field>
            <#if field.keyFlag>
                <#if idType ??>
                    <#if idType == "INPUT">
        ${entity?uncap_first}.set${field.propertyName?cap_first}(IdWorkerProvider.next());
                    </#if>
                </#if>
            </#if>
        </#list>

    <#if cfg.sharding??>
        <#if cfg.sharding.shardingDatabase && cfg.sharding.databaseShardingColumnName == "tenantId">
        ${entity?uncap_first}.set${cfg.sharding.databaseShardingColumnName?cap_first}(MultientyContext.getTenant().getTenantId());
        </#if>
    </#if>
        saveTE(${entity?uncap_first});
        <#list table.fields as field>
            <#if field.keyFlag>
        parameter.set${field.propertyName?cap_first}(${entity?uncap_first}.get${field.propertyName?cap_first}());
            </#if>
        </#list>
    }


    <#list table.fields as field>
        <#if field.propertyName == "status">
        <#assign lowerEntity=entity?uncap_first>
    @Override
    public void enable(${entity}EnableParameter parameter) {
        ${entity} ${lowerEntity} = new ${entity}();
        BeanUtils.copyProperties(parameter, ${lowerEntity});
        ${lowerEntity}.setStatus(StatusEnum.NORMAL.getCode());
    <#if cfg.sharding??>
        update(${entity?uncap_first}, new LambdaQueryWrapper<${entity}>()
        <#if cfg.sharding.shardingDatabase>
                .eq(${entity}::get${cfg.sharding.databaseShardingColumnName?cap_first}, parameter.get${cfg.sharding.databaseShardingColumnName?cap_first}())
        </#if>
        <#if cfg.sharding.shardingTable>
                .eq(${entity}::get${cfg.sharding.tableShardingColumnName?cap_first}, parameter.get${cfg.sharding.tableShardingColumnName?cap_first}())
        </#if>
        <#list table.fields as field>
            <#if field.keyFlag>
                .eq(${entity}::get${field.propertyName?cap_first}, parameter.get${field.propertyName?cap_first}()));
            </#if>
        </#list>
    <#else>
        updateByIdTE(${lowerEntity});
    </#if>

    }

    @Override
    public void disable(${entity}DisableParameter parameter) {
        ${entity} ${lowerEntity} = new ${entity}();
        BeanUtils.copyProperties(parameter, ${lowerEntity});
        ${lowerEntity}.setStatus(StatusEnum.DISABLED.getCode());
    <#if cfg.sharding??>
        update(${entity?uncap_first}, new LambdaQueryWrapper<${entity}>()
        <#if cfg.sharding.shardingDatabase>
                .eq(${entity}::get${cfg.sharding.databaseShardingColumnName?cap_first}, parameter.get${cfg.sharding.databaseShardingColumnName?cap_first}())
        </#if>
        <#if cfg.sharding.shardingTable>
                .eq(${entity}::get${cfg.sharding.tableShardingColumnName?cap_first}, parameter.get${cfg.sharding.tableShardingColumnName?cap_first}())
        </#if>
        <#list table.fields as field>
            <#if field.keyFlag>
                .eq(${entity}::get${field.propertyName?cap_first}, parameter.get${field.propertyName?cap_first}()));
            </#if>
        </#list>
    <#else>
        updateByIdTE(${lowerEntity});
    </#if>
    }
        </#if>
    </#list>
}
</#if>
