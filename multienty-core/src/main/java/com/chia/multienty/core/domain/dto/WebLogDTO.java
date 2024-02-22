package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.WebLog;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * web请求记录 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="WebLogDTO", description="web请求记录DTO对象")
public class WebLogDTO extends WebLog {
}
