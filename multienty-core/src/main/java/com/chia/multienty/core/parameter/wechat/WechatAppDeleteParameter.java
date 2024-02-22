package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 微信应用删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatAppDeleteParameter",description = "微信应用删除请求")
public class WechatAppDeleteParameter {

     @LogMetaId
     private Long programId;
}
