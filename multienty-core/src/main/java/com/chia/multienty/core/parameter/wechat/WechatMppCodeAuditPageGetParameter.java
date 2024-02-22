package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatMppCodeAuditDTO;
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
@ApiModel(value = "WechatMppCodeAuditPageGetParameter",description = "微信小程序代码审核单分页列表查询请求")
public class WechatMppCodeAuditPageGetParameter extends DefaultListGetParameter<WechatMppCodeAuditDTO> {

    /**
     * 审核单编号
     */
     @ApiModelProperty(value = "审核单编号列表")
     private List<Long> auditIds;
}
