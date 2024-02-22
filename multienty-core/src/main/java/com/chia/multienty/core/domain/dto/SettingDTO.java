package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Setting;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 系统配置信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SettingDTO", description="系统配置信息DTO对象")
public class SettingDTO extends Setting {
}
