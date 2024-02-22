package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.NoticeDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 系统通知信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "NoticePageGetParameter",description = "系统通知信息分页列表查询请求")
public class NoticePageGetParameter extends DefaultListGetParameter<NoticeDTO> {

    /**
     * 消息id
     */
     @ApiModelProperty(value = "消息id列表")
     private List<Long> noticeIds;
}
