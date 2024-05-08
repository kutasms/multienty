package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 应用实例保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "AppInstanceSaveParameter",description = "应用实例保存请求")
@Accessors(chain = true)
public class AppInstanceSaveParameter {

    /**
     * 实例编号
     */
    @ApiModelProperty(value = "实例编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long instanceId;
    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    private Long appId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private Long tenantId;
    /**
     * 运行模式
     */
    @ApiModelProperty(value = "运行模式")
    private Byte runningMode;
    /**
     * 截至日期
     */
    @ApiModelProperty(value = "截至日期")
    private LocalDateTime deadline;
    /**
     * 实例状态
     */
    @ApiModelProperty(value = "实例状态")
    private String state;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String alias;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
