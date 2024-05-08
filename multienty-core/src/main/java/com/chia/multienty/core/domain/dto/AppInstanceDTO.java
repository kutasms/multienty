package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.AppInstance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 应用实例 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="AppInstanceDTO", description="应用实例DTO对象")
public class AppInstanceDTO extends AppInstance {
}
