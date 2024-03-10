package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.OauthRefreshTokenDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 更新令牌分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthRefreshTokenPageGetParameter",description = "更新令牌分页列表查询请求")
@Accessors(chain = true)
public class OauthRefreshTokenPageGetParameter extends DefaultListGetParameter<OauthRefreshTokenDTO>{

}
