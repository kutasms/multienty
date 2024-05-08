package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序模版保存请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatMppTemplateSaveParameter",description = "微信小程序模版保存请求")
@Accessors(chain = true)
public class WechatMppTemplateSaveParameter {

    /**
     * 微信小程序模版编号
     */
    @ApiModelProperty(value = "微信小程序模版编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long mppTemplateId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
    /**
     * 模版类型
     */
    @ApiModelProperty(value = "模版类型")
    private Short type;
    /**
     * 微信小程序编号
     */
    @ApiModelProperty(value = "微信小程序编号")
    private Long programId;
    /**
     * 模版编号
     */
    @ApiModelProperty(value = "模版编号")
    private String templateId;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
