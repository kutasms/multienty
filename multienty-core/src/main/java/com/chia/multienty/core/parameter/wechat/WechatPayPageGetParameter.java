package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatPayDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 微信支付分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatPayPageGetParameter",description = "微信支付分页列表查询请求")
public class WechatPayPageGetParameter extends DefaultListGetParameter<WechatPayDTO> {

    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号列表")
     private List<Long> wxPayIds;
}
