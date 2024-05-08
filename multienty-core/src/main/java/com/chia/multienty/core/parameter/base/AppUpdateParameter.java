package com.chia.multienty.core.parameter.base;

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
 * 应用更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "AppUpdateParameter",description = "应用更新请求")
@Accessors(chain = true)
public class AppUpdateParameter {

    /**
     * 应用编号
     */
     @ApiModelProperty(value = "应用编号")
     @LogMetaId
     private Long appId;
    /**
     * 应用号码
     */
     @ApiModelProperty(value = "应用号码")
     private String appNo;
    /**
     * 名称
     */
     @ApiModelProperty(value = "名称")
     private String name;
    /**
     * 官网地址
     */
     @ApiModelProperty(value = "官网地址")
     private String homePage;
    /**
     * 应用详情
     */
     @ApiModelProperty(value = "应用详情")
     private String details;
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
