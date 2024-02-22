package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 微信小程序昵称审核单
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_mpp_nick_name_audit")
@ApiModel(value="WechatMppNickNameAudit对象", description="微信小程序昵称审核单")
public class WechatMppNickNameAudit extends KutaBaseEntity {


    /**
     * 审核单编号
     */
    @ApiModelProperty(value = "审核单编号")
    @TableId(value = "audit_id", type = IdType.INPUT)
    private Long auditId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 小程序appid
     */
    @ApiModelProperty(value = "小程序appid")
    @TableField("`app_id`")
    private String appId;

    /**
     * 材料说明
     */
    @ApiModelProperty(value = "材料说明")
    @TableField("`wording`")
    private String wording;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField("`nick_name`")
    private String nickName;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因")
    @TableField("`reason`")
    private String reason;


}
