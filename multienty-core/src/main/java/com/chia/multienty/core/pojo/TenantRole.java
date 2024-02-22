package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户角色关联
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_tenant_role")
@ApiModel(value="TenantRole对象", description="租户角色关联")
public class TenantRole extends KutaBaseEntity {


    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @TableId(value = "tr_id", type = IdType.AUTO)
    private Long trId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    @TableField("`role_id`")
    private Long roleId;


}
