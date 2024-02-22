package com.chia.multienty.core.domain.basic;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PagedParameter<T> implements Serializable {
    @ApiModelProperty("当前页")
    private Integer currentPage = 1;
    @ApiModelProperty("页大小")
    private Integer pageSize = 10;

    @JsonIgnore
    public Page<T> getPageObj() {
        return new Page<T>(currentPage, pageSize);
    }
}
