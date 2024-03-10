package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 租户更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */

@Data
@ApiModel(value = "TenantUpdateParameter",description = "租户更新请求")
@Accessors(chain = true)
public class TenantUpdateParameter {

    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     @LogMetaId
     private Long tenantId;
    /**
     * 租户号码
     */
     @ApiModelProperty(value = "租户号码")
     private String tenantNo;
    /**
     * 帐号
     */
     @ApiModelProperty(value = "帐号")
     private String username;
    /**
     * 密码
     */
     @ApiModelProperty(value = "密码")
     private String password;
    /**
     * 是否已过期
     */
     @ApiModelProperty(value = "是否已过期")
     private Boolean expired;
    /**
     * 是否已锁定
     */
     @ApiModelProperty(value = "是否已锁定")
     private Boolean locked;
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
     * 令牌
     */
     @ApiModelProperty(value = "令牌")
     private String token;
    /**
     * 头像地址
     */
     @ApiModelProperty(value = "头像地址")
     private String avatar;
    /**
     * 租户类型
     */
     @ApiModelProperty(value = "租户类型")
     private Byte type;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 是否已删除
     */
     @ApiModelProperty(value = "是否已删除")
     private Boolean deleted;
}
