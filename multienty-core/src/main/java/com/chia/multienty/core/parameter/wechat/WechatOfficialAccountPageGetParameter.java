package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.WechatOfficialAccountDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信公众号账户分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatOfficialAccountPageGetParameter",description = "微信公众号账户分页列表查询请求")
@Accessors(chain = true)
public class WechatOfficialAccountPageGetParameter extends DefaultListGetParameter<WechatOfficialAccountDTO>{

    /**
     * 微信公众号账户编号
     */
     @ApiModelProperty(value = "微信公众号账户编号列表")
     private List<Long> woaIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
