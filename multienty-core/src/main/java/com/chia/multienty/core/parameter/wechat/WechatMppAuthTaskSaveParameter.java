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
 * 微信小程序认证任务保存请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@ApiModel(value = "WechatMppAuthTaskSaveParameter",description = "微信小程序认证任务保存请求")
@Accessors(chain = true)
public class WechatMppAuthTaskSaveParameter {

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long taskId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
    /**
     * 商家小程序编号
     */
    @ApiModelProperty(value = "商家小程序编号")
    private String appId;
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer taskStatus;
    /**
     * 审核单状态
     */
    @ApiModelProperty(value = "审核单状态")
    private Integer applyStatus;
    /**
     * 微信任务编号
     */
    @ApiModelProperty(value = "微信任务编号")
    private String wxTaskId;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String params;
    /**
     * 审核机构名称
     */
    @ApiModelProperty(value = "审核机构名称")
    private String dispatchInfoProvider;
    /**
     * 失败时间
     */
    @ApiModelProperty(value = "失败时间")
    private String dispatchInfoContact;
    /**
     * 派单时间戳（秒）
     */
    @ApiModelProperty(value = "派单时间戳（秒）")
    private Long dispatchInfoDispatchTime;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String message;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
