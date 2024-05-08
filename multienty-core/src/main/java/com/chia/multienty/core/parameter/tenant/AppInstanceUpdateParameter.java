package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 应用实例更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "AppInstanceUpdateParameter",description = "应用实例更新请求")
@Accessors(chain = true)
public class AppInstanceUpdateParameter {

    /**
     * 实例编号
     */
     @ApiModelProperty(value = "实例编号")
     @LogMetaId
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
