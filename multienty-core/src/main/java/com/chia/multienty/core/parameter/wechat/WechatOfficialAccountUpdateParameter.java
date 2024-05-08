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
 * 微信公众号账户更新请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatOfficialAccountUpdateParameter",description = "微信公众号账户更新请求")
@Accessors(chain = true)
public class WechatOfficialAccountUpdateParameter {

    /**
     * 微信公众号账户编号
     */
     @ApiModelProperty(value = "微信公众号账户编号")
     @LogMetaId
     private Long woaId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     @NotNull
     private Long tenantId;
    /**
     * 应用编号
     */
     @ApiModelProperty(value = "应用编号")
     private String appId;
    /**
     * 应用密钥
     */
     @ApiModelProperty(value = "应用密钥")
     private String appSecret;
    /**
     * 令牌
     */
     @ApiModelProperty(value = "令牌")
     private String token;
    /**
     * AES密钥
     */
     @ApiModelProperty(value = "AES密钥")
     private String aesKey;
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
