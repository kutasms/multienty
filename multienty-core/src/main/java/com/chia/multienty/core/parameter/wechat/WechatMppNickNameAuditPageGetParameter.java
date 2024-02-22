package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 微信小程序昵称审核单分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatMppNickNameAuditPageGetParameter",description = "微信小程序昵称审核单分页列表查询请求")
public class WechatMppNickNameAuditPageGetParameter extends DefaultListGetParameter<WechatMppNickNameAuditDTO> {

    /**
     * 审核单编号
     */
     @ApiModelProperty(value = "审核单编号列表")
     private List<Long> auditIds;
}
