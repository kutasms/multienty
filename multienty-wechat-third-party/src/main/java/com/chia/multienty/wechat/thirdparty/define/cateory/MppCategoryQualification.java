package com.chia.multienty.wechat.thirdparty.define.cateory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 类目资质
 */
@Data
public class MppCategoryQualification {
    /**
     * 资质证明列表
     */
    @JsonProperty(value = "exter_list")
    private List<MppCategoryQualificationOuter> outerList;
    /**
     * 备注
     */
    private String remark;
}
