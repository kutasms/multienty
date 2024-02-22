package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-11
 */

@Data
@ApiModel(value = "TenantSaveParameter",description = "租户保存请求")
public class TenantSaveParameter {

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long tenantId;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String liaison;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String contactNumber;
    /**
     * 租户类型
     */
    @ApiModelProperty(value = "租户类型")
    private Byte type;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
