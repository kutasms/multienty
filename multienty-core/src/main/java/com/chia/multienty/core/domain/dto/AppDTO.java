package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.App;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 应用 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="AppDTO", description="应用DTO对象")
public class AppDTO extends App {
}
