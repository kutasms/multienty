package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 应用删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "AppDeleteParameter",description = "应用删除请求")
@Accessors(chain = true)
public class AppDeleteParameter {

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @LogMetaId
    private Long appId;

}
