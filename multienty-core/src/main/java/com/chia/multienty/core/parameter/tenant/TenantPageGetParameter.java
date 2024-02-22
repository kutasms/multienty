package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 租户信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "TenantPageGetParameter",description = "租户信息分页列表查询请求")
public class TenantPageGetParameter extends DefaultListGetParameter<TenantDTO> {

    /**
     * 租户id
     */
     @ApiModelProperty(value = "租户id列表")
     private List<Long> tenantIds;
}
