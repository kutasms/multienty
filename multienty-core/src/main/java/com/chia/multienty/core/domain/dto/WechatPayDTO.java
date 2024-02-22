package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.WechatPay;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 微信支付 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="WechatPayDTO", description="微信支付DTO对象")
public class WechatPayDTO extends WechatPay {
}
