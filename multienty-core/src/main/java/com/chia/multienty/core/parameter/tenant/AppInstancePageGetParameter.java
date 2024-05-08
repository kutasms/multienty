package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.domain.dto.AppInstanceDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * <p>
 * 应用实例分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "AppInstancePageGetParameter",description = "应用实例分页列表查询请求")
@Accessors(chain = true)
public class AppInstancePageGetParameter extends DefaultListGetParameter<AppInstanceDTO>{

    /**
     * 实例编号
     */
     @ApiModelProperty(value = "实例编号列表")
     private List<Long> instanceIds;
    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
     private Long appId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private Long tenantId;
}
