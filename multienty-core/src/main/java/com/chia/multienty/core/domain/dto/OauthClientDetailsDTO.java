package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.OauthClientDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 客户端信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="OauthClientDetailsDTO", description="客户端信息DTO对象")
public class OauthClientDetailsDTO extends OauthClientDetails {
}
