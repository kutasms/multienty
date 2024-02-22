package com.chia.multienty.wechat.thirdparty.define.code;

import lombok.Data;

/**
 * 分阶段发布计划详情
 */
@Data
public class GrayReleasePlan {
    /**
     * 0:初始状态 1:执行中 2:暂停中 3:执行完毕 4:被删除
     */
    private Integer status;
    /**
     * 分阶段发布计划的创建时间
     */
    private Long createTimestamp;
    /**
     * 当前的灰度比例
     */
    private Integer grayPercentage;
    /**
     * true表示支持按项目成员灰度
     */
    private Boolean supportDebugerFirst;
    /**
     * true表示支持按体验成员灰度
     */
    private Boolean supportExperiencerFirst;
}
