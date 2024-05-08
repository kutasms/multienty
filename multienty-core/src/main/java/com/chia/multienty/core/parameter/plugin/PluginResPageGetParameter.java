package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.PluginResDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件资源分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */

@Data
@ApiModel(value = "PluginResPageGetParameter",description = "插件资源分页列表查询请求")
@Accessors(chain = true)
public class PluginResPageGetParameter extends DefaultListGetParameter<PluginResDTO>{

    /**
     * 插件资源编号
     */
     @ApiModelProperty(value = "插件资源编号列表")
     private List<Long> resIds;
}
