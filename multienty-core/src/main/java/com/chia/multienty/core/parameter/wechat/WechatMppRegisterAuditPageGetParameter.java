package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序注册审核单分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@ApiModel(value = "WechatMppRegisterAuditPageGetParameter",description = "微信小程序注册审核单分页列表查询请求")
@Accessors(chain = true)
public class WechatMppRegisterAuditPageGetParameter extends DefaultListGetParameter<WechatMppRegisterAuditDTO>{

    /**
     * 审核编号
     */
     @ApiModelProperty(value = "审核编号列表")
     private List<Long> auditIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
