package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信公众号账户详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatOfficialAccountDetailGetParameter",description = "微信公众号账户详情获取请求")
public class WechatOfficialAccountDetailGetParameter {
    /**
     * 微信公众号账户编号
     */
     @ApiModelProperty(value = "微信公众号账户编号")
     private Long woaId;
}
