package ${cfg.package_dto};

import ${package.Entity}.${entity};
<#if swagger??>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * ${table.comment} DTO对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
<#if swagger??>
@ApiModel(value="${entity}DTO", description="${table.comment!}DTO对象")
</#if>
public class ${entity}DTO extends ${entity} {
}
