package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * Json配置
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_json_config")
@ApiModel(value="JsonConfig对象", description="Json配置")
public class JsonConfig extends KutaBaseEntity {


    /**
     * 配置编号
     */
    @ApiModelProperty(value = "配置编号")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Long configId;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField("`alias`")
    private String alias;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    @TableField("`remark`")
    private String remark;

    /**
     * JSON数据
     */
    @ApiModelProperty(value = "JSON数据")
    @TableField("`data`")
    private String data;

    /**
     * 默认数据
     */
    @ApiModelProperty(value = "默认数据")
    @TableField("`default_data`")
    private String defaultData;

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


}
