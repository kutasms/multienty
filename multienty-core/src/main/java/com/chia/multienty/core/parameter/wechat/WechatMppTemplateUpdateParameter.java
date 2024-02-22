package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序模版更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatMppTemplateUpdateParameter",description = "微信小程序模版更新请求")
public class WechatMppTemplateUpdateParameter {

    /**
     * 微信小程序模版编号
     */
     @ApiModelProperty(value = "微信小程序模版编号")
     @LogMetaId
     private Long mppTemplateId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
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
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 是否已删除
     */
     @ApiModelProperty(value = "是否已删除")
     private Boolean deleted;
}
