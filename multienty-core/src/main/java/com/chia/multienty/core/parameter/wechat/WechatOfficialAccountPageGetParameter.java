package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatOfficialAccountDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 微信公众号账户分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatOfficialAccountPageGetParameter",description = "微信公众号账户分页列表查询请求")
public class WechatOfficialAccountPageGetParameter extends DefaultListGetParameter<WechatOfficialAccountDTO> {

    /**
     * 微信公众号账户编号
     */
     @ApiModelProperty(value = "微信公众号账户编号列表")
     private List<Long> woaIds;
}
