package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.OauthClientDetailsDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 客户端信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthClientDetailsPageGetParameter",description = "客户端信息分页列表查询请求")
@Accessors(chain = true)
public class OauthClientDetailsPageGetParameter extends DefaultListGetParameter<OauthClientDetailsDTO>{

    /**
     * 客户端ID
     */
     @ApiModelProperty(value = "客户端ID列表")
     private List<String> clientIds;
}
