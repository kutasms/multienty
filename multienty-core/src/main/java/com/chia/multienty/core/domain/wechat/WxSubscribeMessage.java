package com.chia.multienty.core.domain.wechat;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WxSubscribeMessage<T> {

    @JSONField(name = "touser")
    private String receiver;

    @JSONField(name = "template_id")
    private String templateId;

    @JSONField(name = "page")
    private String page;

    @JSONField(name = "data")
    private JSONObject data;

    @JSONField(name = "miniprogram_state")
    private String miniProgramState;

    @JSONField(name = "lang")
    private String lang;
}
