package com.chia.multienty.wechat.thirdparty.parameter.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.oa.WxMedia;
import lombok.Data;

import java.util.Map;

@Data
public class NickNameSetParameter {
    private String nickName;
    private Map<String, WxMedia> mediaMap;
}
