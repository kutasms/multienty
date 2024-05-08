package com.chia.multienty.wechat.thirdparty.sdk.response.member;

import com.chia.multienty.wechat.thirdparty.define.member.MppMember;
import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 解除绑定体验者响应数据
 */
@Data
public class TesterUnbindResponse extends BaseResponse {
    /**
     * 人员信息列表
     */
    @JsonProperty("members")
    private List<MppMember> members;
}
