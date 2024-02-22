package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 微信小程序代码审核单分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */

@Data
@ApiModel(value = "WechatMppRegisterAuditPageGetParameter",description = "微信小程序代码审核单分页列表查询请求")
public class WechatMppRegisterAuditPageGetParameter extends DefaultListGetParameter<WechatMppRegisterAuditDTO> {

    /**
     * 审核编号
     */
     @ApiModelProperty(value = "审核编号列表")
     private List<Long> auditIds;
}
