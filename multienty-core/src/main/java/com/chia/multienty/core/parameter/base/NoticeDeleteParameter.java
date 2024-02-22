package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 通知删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "NoticeDeleteParameter",description = "通知删除请求")
public class NoticeDeleteParameter {

    /**
     * 通知编号
     */
     @ApiModelProperty(value = "通知编号")
     @LogMetaId
     private Long noticeId;
}
