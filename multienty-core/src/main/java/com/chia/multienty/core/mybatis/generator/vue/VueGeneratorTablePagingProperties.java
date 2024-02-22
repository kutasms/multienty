package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

@Data
public class VueGeneratorTablePagingProperties {

    private String layout = "prev, pager, next";
    private Integer pagerCount = 11;

}
