package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.WechatMppTemplateDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序模版分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatMppTemplatePageGetParameter",description = "微信小程序模版分页列表查询请求")
@Accessors(chain = true)
public class WechatMppTemplatePageGetParameter extends DefaultListGetParameter<WechatMppTemplateDTO>{

    /**
     * 微信小程序模版编号
     */
     @ApiModelProperty(value = "微信小程序模版编号列表")
     private List<Long> mppTemplateIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
