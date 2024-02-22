package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.WechatOfficialAccount;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 微信公众号账户 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="WechatOfficialAccountDTO", description="微信公众号账户DTO对象")
public class WechatOfficialAccountDTO extends WechatOfficialAccount {
}
