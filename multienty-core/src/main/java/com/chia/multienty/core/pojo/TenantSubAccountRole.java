package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户子账号角色关联
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_tenant_sub_account_role")
@ApiModel(value="TenantSubAccountRole对象", description="租户子账号角色关联")
public class TenantSubAccountRole extends KutaBaseEntity {


    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @TableId(value = "sar_id", type = IdType.AUTO)
    private Long sarId;

    /**
     * 租户子账号编号
     */
    @ApiModelProperty(value = "租户子账号编号")
    @TableField("`sub_account_id`")
    private Long subAccountId;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    @TableField("`role_id`")
    private Long roleId;


}
