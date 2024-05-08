package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.WechatMppAuthTaskDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 微信小程序认证任务分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */

@Data
@ApiModel(value = "WechatMppAuthTaskPageGetParameter",description = "微信小程序认证任务分页列表查询请求")
@Accessors(chain = true)
public class WechatMppAuthTaskPageGetParameter extends DefaultListGetParameter<WechatMppAuthTaskDTO>{

    /**
     * 任务编号
     */
     @ApiModelProperty(value = "任务编号列表")
     private List<Long> taskIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
