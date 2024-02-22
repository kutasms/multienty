package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatMppTemplateDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 微信小程序模版分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatMppTemplatePageGetParameter",description = "微信小程序模版分页列表查询请求")
public class WechatMppTemplatePageGetParameter extends DefaultListGetParameter<WechatMppTemplateDTO> {

    /**
     * 微信小程序模版编号
     */
     @ApiModelProperty(value = "微信小程序模版编号列表")
     private List<Long> mppTemplateIds;
}
