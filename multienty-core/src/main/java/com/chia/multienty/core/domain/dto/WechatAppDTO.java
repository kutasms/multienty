package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppTemplate;
import com.chia.multienty.core.pojo.WechatPay;
import com.chia.multienty.core.domain.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 微信应用 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="WechatAppDTO", description="微信应用DTO对象")
public class WechatAppDTO extends WechatApp {
    /**
     * 模版列表
     */
    @ApiModelProperty("模版列表")
    private List<WechatMppTemplate> templates;
    /**
     * 支付配置
     */
    @ApiModelProperty("支付配置")
    private WechatPay pay;

    /**
     * 获取授权token是否已过期
     * @return
     */
    private Boolean isAuthorizerAccessTokenExpired() {
        if(this.getStatus().equals(StatusEnum.CREATED.getCode())) {
            return true;
        }
        return this.getAuthorizerTokenTime()
                .plusSeconds(this.getAuthorizerTokenExpiresIn())
                .isBefore(LocalDateTime.now());
    }
}
