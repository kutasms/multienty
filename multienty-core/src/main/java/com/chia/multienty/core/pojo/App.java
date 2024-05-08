package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应用
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_app")
@ApiModel(value="App对象", description="应用")
public class App extends KutaBaseEntity {


    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @TableId(value = "app_id", type = IdType.INPUT)
    private Long appId;

    /**
     * 应用号码
     */
    @ApiModelProperty(value = "应用号码")
    @TableField("`app_no`")
    private String appNo;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @TableField("`name`")
    private String name;

    /**
     * 官网地址
     */
    @ApiModelProperty(value = "官网地址")
    @TableField("`home_page`")
    private String homePage;

    /**
     * 应用详情
     */
    @ApiModelProperty(value = "应用详情")
    @TableField("`details`")
    private String details;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`update_time`", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    @TableField(value = "`deleted`", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
