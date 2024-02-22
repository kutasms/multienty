package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.PermissionDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 权限菜单信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "PermissionPageGetParameter",description = "权限菜单信息分页列表查询请求")
public class PermissionPageGetParameter extends DefaultListGetParameter<PermissionDTO> {

    /**
     * 权限id
     */
     @ApiModelProperty(value = "权限id列表")
     private List<Long> permissionIds;
}
