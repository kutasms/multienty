package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 租户子账号更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@ApiModel(value = "TenantSubAccountUpdateParameter",description = "租户子账号更新请求")
@Accessors(chain = true)
public class TenantSubAccountUpdateParameter {

    /**
     * 子账号编号
     */
     @ApiModelProperty(value = "子账号编号")
     @LogMetaId
     private Long subAccountId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     private Long tenantId;
    /**
     * 账户
     */
     @ApiModelProperty(value = "账户")
     private String username;
    /**
     * 密码
     */
     @ApiModelProperty(value = "密码")
     private String password;
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
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 乐观锁版本号
     */
     @ApiModelProperty(value = "乐观锁版本号")
     private Long version;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;

    /**
     * 角色编号列表
     */
    @ApiModelProperty(value = "角色编号列表")
    private List<Long> roleIds;
}
