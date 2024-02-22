package com.chia.multienty.core.domain.vo.permission;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VueRouteMeta {
    /**
     * 标题
     * */
    @ApiModelProperty("标题")
    private String title;
    /**
     * 权限类型（根据不同的类型进行不同的渲染）
     * */
    @ApiModelProperty("权限类型（根据不同的类型进行不同的渲染）")
    private PermissionType type;
    /**
     * 图标
     * */
    @ApiModelProperty("图标")
    private String icon;
    /**
     * 是否固定在多页面标签行
     * */
    @ApiModelProperty("是否固定在多页面标签行")
    private Boolean affix;
    /**
     * 权限列表
     * */
    @ApiModelProperty("权限列表")
    private List<String> permissions;

    /**
     * 权限编号
     */
    @ApiModelProperty("权限编号")
    private Long permissionId;
}
