package com.chia.multienty.wechat.thirdparty.sdk.response.member;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.member.MppMember;
import lombok.Data;

import java.util.List;

/**
 * 获取体验者列表响应数据
 */
@Data
public class TesterGetResponse extends BaseResponse {
    /**
     * 人员信息列表
     */
    private List<MppMember> members;
}
