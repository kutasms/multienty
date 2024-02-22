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
 * 角色权限关联数据
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_role_permission")
@ApiModel(value="RolePermission对象", description="角色权限关联数据")
public class RolePermission extends KutaBaseEntity {


    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @TableId(value = "rp_id", type = IdType.INPUT)
    private Long rpId;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    @TableField("`role_id`")
    private Long roleId;

    /**
     * 权限编号
     */
    @ApiModelProperty(value = "权限编号")
    @TableField("`permission_id`")
    private Long permissionId;


}
