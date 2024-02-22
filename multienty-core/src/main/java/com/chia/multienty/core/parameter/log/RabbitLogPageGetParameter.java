package com.chia.multienty.core.parameter.log;

import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * Rabbit MQ日志信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RabbitLogPageGetParameter",description = "Rabbit MQ日志信息分页列表查询请求")
public class RabbitLogPageGetParameter extends DefaultListGetParameter<RabbitLogDTO> {

    /**
     * 发送记录编号
     */
     @ApiModelProperty(value = "发送记录编号列表")
     private List<Long> rids;

    /**
     * 时间刻度数
     */
     private Long timestamp;
}
