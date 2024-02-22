package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 搜索模拟对象
 * </p>
 *
 * @author kutasms
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mt_search_entity")
@ApiModel(value="SearchEntity对象", description="搜索模拟对象")
public class SearchEntity implements Serializable {
    private String status;
    private Boolean deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long cityId;
    private Boolean mockData;
    private Long version;


}
