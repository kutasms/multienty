package com.chia.multienty.wechat.thirdparty.sdk.request.template;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplatedRaftListGetResponse;

/**
 * 获取草稿箱列表
 */
@WxApi(url = ThirdPlatformApis.Template.GET_TEMPLATED_RAFT_LIST, method = WxApi.Method.GET)
public class TemplatedRaftListGetRequest extends ComponentBaseRequest<TemplatedRaftListGetResponse> {

}
