package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 微信支付
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_pay")
@ApiModel(value="WechatPay对象", description="微信支付")
public class WechatPay extends KutaBaseEntity {


    /**
     * 微信支付编号
     */
    @ApiModelProperty(value = "微信支付编号")
    @TableId(value = "wx_pay_id", type = IdType.AUTO)
    private Long wxPayId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    @TableField("`mch_id`")
    private String mchId;

    /**
     * 证书路径
     */
    @ApiModelProperty(value = "证书路径")
    @TableField("`cert_path`")
    private String certPath;

    /**
     * 商户私钥路径
     */
    @ApiModelProperty(value = "商户私钥路径")
    @TableField("`private_key_path`")
    private String privateKeyPath;

    /**
     * 程序编号
     */
    @ApiModelProperty(value = "程序编号")
    @TableField("`program_id`")
    private Long programId;

    /**
     * 商户证书序列号
     */
    @ApiModelProperty(value = "商户证书序列号")
    @TableField("`serial_number`")
    private String serialNumber;

    /**
     * v3版本key
     */
    @ApiModelProperty(value = "v3版本key")
    @TableField("`api_v3_key`")
    private String apiV3Key;

    /**
     * v2版本key
     */
    @ApiModelProperty(value = "v2版本key")
    @TableField("`api_v2_key`")
    private String apiV2Key;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`update_time`", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    @TableField(value = "`deleted`", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
