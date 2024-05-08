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
 * 权限集
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_app_func_scope")
@ApiModel(value="WechatAppFuncScope对象", description="权限集")
public class WechatAppFuncScope extends KutaBaseEntity {


    /**
     * 权限集关联编号
     */
    @ApiModelProperty(value = "权限集关联编号")
    @TableId(value = "fs_id", type = IdType.AUTO)
    private Long fsId;

    /**
     * app编号
     */
    @ApiModelProperty(value = "app编号")
    @TableField("`program_id`")
    private Long programId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 权限集id
     */
    @ApiModelProperty(value = "权限集id")
    @TableField("`func_scope_id`")
    private Integer funcScopeId;

    /**
     * 权限集类型
     */
    @ApiModelProperty(value = "权限集类型")
    @TableField("`type`")
    private Integer type;


}
