package com.chia.multienty.core.parameter.log;

import com.chia.multienty.core.domain.dto.WebLogDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * web请求记录分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "WebLogPageGetParameter",description = "web请求记录分页列表查询请求")
public class WebLogPageGetParameter extends DefaultListGetParameter<WebLogDTO> {

    /**
     * 请求记录id
     */
     @ApiModelProperty(value = "请求记录id列表")
     private List<Long> logIds;

    private Long metaId;
    private Integer type;

    private String target;
}
