package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信公众号账户保存请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatOfficialAccountSaveParameter",description = "微信公众号账户保存请求")
@Accessors(chain = true)
public class WechatOfficialAccountSaveParameter {

    /**
     * 微信公众号账户编号
     */
    @ApiModelProperty(value = "微信公众号账户编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
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
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
