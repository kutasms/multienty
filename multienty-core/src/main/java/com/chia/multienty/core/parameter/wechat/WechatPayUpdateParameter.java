package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信支付更新请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatPayUpdateParameter",description = "微信支付更新请求")
@Accessors(chain = true)
public class WechatPayUpdateParameter {

    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号")
     @LogMetaId
     private Long wxPayId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     @NotNull
     private Long tenantId;
    /**
     * 商户号
     */
     @ApiModelProperty(value = "商户号")
     private String mchId;
    /**
     * 证书路径
     */
     @ApiModelProperty(value = "证书路径")
     private String certPath;
    /**
     * 商户私钥路径
     */
     @ApiModelProperty(value = "商户私钥路径")
     private String privateKeyPath;
    /**
     * 商户证书序列号
     */
     @ApiModelProperty(value = "商户证书序列号")
     private String serialNumber;
    /**
     * v3版本key
     */
     @ApiModelProperty(value = "v3版本key")
     private String apiV3Key;
    /**
     * v2版本key
     */
     @ApiModelProperty(value = "v2版本key")
     private String apiV2Key;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 是否已删除
     */
     @ApiModelProperty(value = "是否已删除")
     private Boolean deleted;
}
