package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信支付详情获取请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "WechatPayDetailGetParameter",description = "微信支付详情获取请求")
public class WechatPayDetailGetParameter {
    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号")
     private Long wxPayId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
