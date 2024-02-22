package com.chia.multienty.core.parameter.log;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * Rabbit MQ日志信息保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "RabbitLogSaveParameter",description = "Rabbit MQ日志信息保存请求")
public class RabbitLogSaveParameter {

    /**
     * 发送记录编号
     */
    @ApiModelProperty(value = "发送记录编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long rid;
    /**
     * 键
     */
    @ApiModelProperty(value = "键")
    private String key;
    /**
     * 元编号（可以是订单号、交易号、客户编号等）
     */
    @ApiModelProperty(value = "元编号（可以是订单号、交易号、客户编号等）")
    private Long metaId;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sentTime;
    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    private String message;
    /**
     * 预定发送时间刻度数
     */
    @ApiModelProperty(value = "预定发送时间刻度数")
    private Long timestamp;
    /**
     * 延迟时间
     */
    @ApiModelProperty(value = "延迟时间")
    private Long delayTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 错误消息
     */
    @ApiModelProperty(value = "错误消息")
    private String errorMsg;
    /**
     * 路由键
     */
    @ApiModelProperty(value = "路由键")
    private String routingKey;
    /**
     * 数据类型 STRING-字符串 JACKSON FASTJSON
     */
    @ApiModelProperty(value = "数据类型 STRING-字符串 JACKSON FASTJSON")
    private String dataType;
    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private String boType;
    /**
     * 是否延迟消息
     */
    @ApiModelProperty(value = "是否延迟消息")
    private Boolean delayed;
    /**
     * 是否独立模式
     */
    @ApiModelProperty(value = "是否独立模式")
    private Boolean idempotent;
    /**
     * 半开模式
     */
    @ApiModelProperty(value = "半开模式")
    private Boolean halfMode;
}
