package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.SecretAuthDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 密钥授权分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "SecretAuthPageGetParameter",description = "密钥授权分页列表查询请求")
@Accessors(chain = true)
public class SecretAuthPageGetParameter extends DefaultListGetParameter<SecretAuthDTO>{

    /**
     * 密钥授权编号
     */
     @ApiModelProperty(value = "密钥授权编号列表")
     private List<Long> secAuthIds;
}
