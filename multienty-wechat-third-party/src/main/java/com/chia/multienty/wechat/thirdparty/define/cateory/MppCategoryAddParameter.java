package com.chia.multienty.wechat.thirdparty.define.cateory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 小程序类目添加参数
 */
@Data
public class MppCategoryAddParameter {
    /**
     * 一级类目 ID
     */
    private Long first;
    /**
     * 二级类目 ID
     */
    private Long second;

    /**
     * 资质信息列表。如果需要资质的类目，则该字段必填
     */
    @JsonProperty(value = "certicates")
    private List<MppCertificate> certificates;
}
