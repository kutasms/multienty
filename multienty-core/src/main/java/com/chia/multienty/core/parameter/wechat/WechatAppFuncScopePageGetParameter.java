package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.domain.dto.WechatAppFuncScopeDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 权限集分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatAppFuncScopePageGetParameter",description = "权限集分页列表查询请求")
public class WechatAppFuncScopePageGetParameter extends DefaultListGetParameter<WechatAppFuncScopeDTO> {

    /**
     * 权限集关联编号
     */
     @ApiModelProperty(value = "权限集关联编号列表")
     private List<Long> fsIds;
}
