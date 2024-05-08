package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信公众号账户删除请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatOfficialAccountDeleteParameter",description = "微信公众号账户删除请求")
@Accessors(chain = true)
public class WechatOfficialAccountDeleteParameter {

    /**
     * 微信公众号账户编号
     */
    @ApiModelProperty(value = "微信公众号账户编号")
    @LogMetaId
    private Long woaId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;

}
