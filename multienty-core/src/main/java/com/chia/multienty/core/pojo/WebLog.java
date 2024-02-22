package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * web请求记录
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_web_log")
@ApiModel(value="WebLog对象", description="web请求记录")
public class WebLog extends KutaBaseEntity {


    /**
     * 请求记录id
     */
    @ApiModelProperty(value = "请求记录id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 请求用户名
     */
    @ApiModelProperty(value = "请求用户名")
    @TableField("`name`")
    private String name;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @TableField("`uid`")
    private Long uid;

    /**
     * 请求接口地址
     */
    @ApiModelProperty(value = "请求接口地址")
    @TableField("`url`")
    private String url;

    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @TableField("`api`")
    private String api;

    /**
     * 请求接口参数
     */
    @ApiModelProperty(value = "请求接口参数")
    @TableField("`args`")
    private String args;

    /**
     * 请求服务平台
     */
    @ApiModelProperty(value = "请求服务平台")
    @TableField("`app_name`")
    private String appName;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    @TableField("`ip`")
    private String ip;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField("`description`")
    private String description;

    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    @TableField("`browser`")
    private String browser;

    /**
     * 耗时
     */
    @ApiModelProperty(value = "耗时")
    @TableField("`time`")
    private Long time;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 关联数据编号
     */
    @ApiModelProperty(value = "关联数据编号")
    @TableField("`meta_id`")
    private Long metaId;

    /**
     * 关联数据编号(扩展2)
     */
    @ApiModelProperty(value = "关联数据编号(扩展2)")
    @TableField("`meta_id_2`")
    private Long metaId2;

    /**
     * 关联数据编号(扩展3)
     */
    @ApiModelProperty(value = "关联数据编号(扩展3)")
    @TableField("`meta_id_3`")
    private Long metaId3;

    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型")
    @TableField("`type`")
    private Short type;


}
