package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.TenantSecretDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户密钥分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "TenantSecretPageGetParameter",description = "租户密钥分页列表查询请求")
@Accessors(chain = true)
public class TenantSecretPageGetParameter extends DefaultListGetParameter<TenantSecretDTO>{

    /**
     * 密钥编号
     */
     @ApiModelProperty(value = "密钥编号列表")
     private List<Long> secretIds;
}
