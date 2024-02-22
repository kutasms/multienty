package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "TenantUpdateParameter",description = "租户信息更新请求")
public class TenantUpdateParameter {

        /**
         * 租户id
         */
        @ApiModelProperty(value = "租户id")
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
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
        /**
         * 状态(NORMAL-正常)
         */
        @ApiModelProperty(value = "状态(NORMAL-正常)")
        private String status;
        /**
         * 是否已删除
         */
        @ApiModelProperty(value = "是否已删除")
        private Boolean deleted;
}
