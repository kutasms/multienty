package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 系统配置分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */

@Data
@ApiModel(value = "SettingPageGetParameter",description = "系统配置分页列表查询请求")
@Accessors(chain = true)
public class SettingPageGetParameter extends DefaultListGetParameter<SettingDTO> {

    /**
     * 配置编号
     */
     @ApiModelProperty(value = "配置编号列表")
     private List<Long> settingIds;
}
