package com.chia.multienty.core.mybatis;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.basic.CityVO;
import com.chia.multienty.core.domain.basic.PagedParameter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 默认列表获取请求
 * */
@Data
@Accessors(chain = true)
@ApiModel(value = "DefaultListGetParameter",description = "默认列表获取请求")
public class DefaultListGetParameter<T> extends PagedParameter<T> {
    /**
     * 搜索关键词
     * */
    @ApiModelProperty("搜索关键词")
    private String keywords;
    /**
     * 创建时间范围
     * */
    @ApiModelProperty("创建时间范围")
    private LocalDateTime[] createTimeDuration;

    /**
     * 日期范围
     * */
    @ApiModelProperty("日期范围")
    private LocalDate[] dateDuration;
    /**
     * 状态
     * */
    @ApiModelProperty("状态")
    private String status;
    /**
     * 状态列表
     * */
    @ApiModelProperty("状态列表")
    private List<String> statusList;

    /**
     * 反序排列列名列表
     * */
    @ApiModelProperty("反序排列列名列表")
    @JsonIgnore
    private List<SFunction<T,?>> orderByDescColumns;
    /**
     * 正序排列列名列表
     * */
    @ApiModelProperty("正序排列列名列表")
    @JsonIgnore
    private List<SFunction<T,?>> orderByAscColumns;
    /**
     * 城市VO对象
     * */
    @ApiModelProperty("城市VO对象")
    private CityVO city;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    private Boolean deleted;

    /**
     * 是否模拟数据
     */
    @ApiModelProperty(value = "是否模拟数据")
    private Boolean mockData;
}
