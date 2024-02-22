package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 余额账单保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountSaveParameter",description = "余额账单保存请求")
public class TenantSubAccountSaveParameter {

    /**
     * 子账号编号
     */
    @ApiModelProperty(value = "子账号编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long subAccountId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private Long tenantId;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
