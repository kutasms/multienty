package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数字
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_num")
@ApiModel(value="Num对象", description="数字")
public class Num extends KutaBaseEntity {


    @TableField("`i`")
    private Integer i;

    @TableField("`y`")
    private Long y;


}
