package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序昵称审核单分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatMppNickNameAuditPageGetParameter",description = "微信小程序昵称审核单分页列表查询请求")
@Accessors(chain = true)
public class WechatMppNickNameAuditPageGetParameter extends DefaultListGetParameter<WechatMppNickNameAuditDTO>{

    /**
     * 审核单编号
     */
     @ApiModelProperty(value = "审核单编号列表")
     private List<Long> auditIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
