package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.OauthAccessTokenDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 访问令牌分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthAccessTokenPageGetParameter",description = "访问令牌分页列表查询请求")
@Accessors(chain = true)
public class OauthAccessTokenPageGetParameter extends DefaultListGetParameter<OauthAccessTokenDTO>{

    /**
     * MD5加密过的username,client_id,scope
     */
     @ApiModelProperty(value = "MD5加密过的username,client_id,scope列表")
     private List<String> authenticationIds;
}
