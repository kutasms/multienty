package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

@Data
public class VueGeneratorPageIndexProperties {

    /**
     * 页面类型分联动(LINKAGE)、表格(TABLE)
     */
    private String pageType = "TABLE";
    /**
     * 联动设置，当page-type为LINKAGE时有效
     */
    private VueGeneratorLinkageProperties linkage;
    private String[] searchInputItems = new String[] {"keywords"};
    private String keywordsPlaceHolder = "请输入搜索关键字";
    private String selectableStatus = "[-1, 3, 8]";
    private VueGeneratorPageIndexTableProperties table;

    /**
     * 是否允许数据创建
     */
    private Boolean dataCreateEnabled = true;
}
