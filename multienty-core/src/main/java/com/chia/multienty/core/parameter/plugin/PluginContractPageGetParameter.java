package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.PluginContractDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件合约分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginContractPageGetParameter",description = "插件合约分页列表查询请求")
@Accessors(chain = true)
public class PluginContractPageGetParameter extends DefaultListGetParameter<PluginContractDTO>{

    /**
     * 插件合约编号
     */
     @ApiModelProperty(value = "插件合约编号列表")
     private List<Long> contractIds;
}
