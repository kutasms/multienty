package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 代码审核单元
 */
@Data
public class CodeAuditItem {
    /**
     * 小程序的页面，可通过"获取小程序的页面列表getCodePage"接口获得
     */
    @JsonProperty("address")
    private String address;
    /**
     * 小程序的标签，用空格分隔，标签至多 10 个，标签长度至多 20
     */
    @JsonProperty("tag")
    private String tag;
    /**
     * 一级类目名称，可通过"getAllCategoryName"接口获取
     */
    @JsonProperty("first_class")
    private String firstClass;
    /**
     * 二级类目名称，可通过"getAllCategoryName"接口获取
     */
    @JsonProperty("second_class")
    private String secondClass;
    /**
     * 三级类目名称，可通过"getAllCategoryName"接口获取
     */
    @JsonProperty("third_class")
    private String thirdClass;
    /**
     * 小程序页面的标题,标题长度至多 32
     */
    @JsonProperty("item_list")
    private String title;
    /**
     * 一级类目id，可通过"getAllCategoryName"接口获取
     */
    @JsonProperty("first_id")
    private Long firstId;
    /**
     * 二级类目id，可通过"getAllCategoryName"接口获取
     */
    @JsonProperty("second_id")
    private Long secondId;
    /**
     * 三级类目id，可通过"getAllCategoryName"接口获取
     */
    @JsonProperty("third_id")
    private Long thirdId;
}
