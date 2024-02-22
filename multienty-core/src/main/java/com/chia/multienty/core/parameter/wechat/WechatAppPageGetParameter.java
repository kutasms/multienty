package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;

import java.util.List;
/**
 * <p>
 * 微信应用分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatAppPageGetParameter",description = "微信应用分页列表查询请求")
public class WechatAppPageGetParameter extends DefaultListGetParameter<WechatAppDTO> {

     private List<Long> programIds;
}
