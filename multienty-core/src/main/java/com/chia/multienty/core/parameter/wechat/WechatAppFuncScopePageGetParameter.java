package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.WechatAppFuncScopeDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 权限集分页列表查询请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatAppFuncScopePageGetParameter",description = "权限集分页列表查询请求")
@Accessors(chain = true)
public class WechatAppFuncScopePageGetParameter extends DefaultListGetParameter<WechatAppFuncScopeDTO>{

    /**
     * 权限集关联编号
     */
     @ApiModelProperty(value = "权限集关联编号列表")
     private List<Long> fsIds;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
