package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.TenantSubAccountDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 余额账单分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountPageGetParameter",description = "余额账单分页列表查询请求")
public class TenantSubAccountPageGetParameter extends DefaultListGetParameter<TenantSubAccountDTO> {

    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号列表")
     private List<Long> subAccountIds;
}
