package com.chia.multienty.wechat.thirdparty.sdk.response.template;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.template.Draft;
import lombok.Data;

import java.util.List;

/**
 * 获取草稿箱列表响应数据
 */
@Data
public class TemplatedRaftListGetResponse extends BaseResponse {
    /**
     * 草稿箱信息
     */
    private List<Draft> draftList;
}
