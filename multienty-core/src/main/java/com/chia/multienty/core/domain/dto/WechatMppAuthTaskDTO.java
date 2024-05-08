package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.WechatMppAuthTask;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 微信小程序认证任务 DTO对象
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="WechatMppAuthTaskDTO", description="微信小程序认证任务DTO对象")
public class WechatMppAuthTaskDTO extends WechatMppAuthTask {
}
