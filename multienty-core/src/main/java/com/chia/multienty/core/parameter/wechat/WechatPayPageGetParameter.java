package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatPayDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;
/**
 * <p>
 * 微信支付分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatPayPageGetParameter",description = "微信支付分页列表查询请求")
@Accessors(chain = true)
public class WechatPayPageGetParameter extends DefaultListGetParameter<WechatPayDTO>{

    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号列表")
     private List<Long> wxPayIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
    /**
     * 程序编号
     */
    @ApiModelProperty(value = "程序编号")
    @NotNull
    private Long programId;
}
