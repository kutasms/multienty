package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

@Data
public class VueGeneratorPageIndexTableProperties {
    /**
     * 是否显示序号
     */
    private Boolean serialNumberEnabled = true;
    /**
     * 分页参数
     */
    private VueGeneratorTablePagingProperties paging = new VueGeneratorTablePagingProperties();
    private String[] tableColumns;
    /**
     * 表格项操作项
     * <p>可选项: detail, edit, delete, web-log</p>
     */
    private String[] tableItemActions = new String[] {"edit", "delete", "web-log"};
}
