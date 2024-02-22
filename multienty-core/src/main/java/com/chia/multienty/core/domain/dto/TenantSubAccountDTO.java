package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.TenantSubAccount;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 余额账单 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantSubAccountDTO", description="余额账单DTO对象")
public class TenantSubAccountDTO extends TenantSubAccount {
}
