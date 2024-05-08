package com.chia.multienty.wechat.thirdparty.define.cateory;

import lombok.Data;

import java.util.List;

@Data
public class MppCategoryQualificationOuter {
    private List<MppCategoryQualificationInner> innerList;
}
