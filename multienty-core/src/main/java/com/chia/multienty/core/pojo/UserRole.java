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
 * 账户角色关联数据
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_user_role")
@ApiModel(value="UserRole对象", description="账户角色关联数据")
public class UserRole extends KutaBaseEntity {


    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @TableId(value = "ur_id", type = IdType.AUTO)
    private Long urId;

    /**
     * 账户id
     */
    @ApiModelProperty(value = "账户id")
    @TableField("`user_id`")
    private Long userId;

    /**
     * 关联角色表id
     */
    @ApiModelProperty(value = "关联角色表id")
    @TableField("`role_id`")
    private Long roleId;


}
