package com.chia.multienty.core.domain.vo.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * VUE通用权限VO
 * */
@Data
@Builder
public class PermissionVO {
    /**
     * 路径
     * */
    private String path;
    /**
     * 组件
     * */
    private String component;
    /**
     * 名称
     * */
    private String name;
    /**
     * 重定向
     * */
    private String redirect = "noRedirect";
    /**
     * 别名
     * */
    @JsonIgnore
    private String alias;
    /**
     * 子路径
     * */
    private List<PermissionVO> children;
    /**
     * 是否忽略大小写
     * */
    @JsonIgnore
    private Boolean caseSensitive = false;
    /**
     * 父级编号
     * */
    private Long pid;
    /**
     * 是否隐藏
     * */
    private Boolean hidden;
    /**
     * 元数据
     * */
    private VueRouteMeta meta;
}
