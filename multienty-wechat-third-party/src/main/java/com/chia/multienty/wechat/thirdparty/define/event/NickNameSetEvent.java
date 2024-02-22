package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class NickNameSetEvent extends BaseEvent{

    @XmlElement(name = "nickname")
    private String nickName;
    private String ret;
    private String reason;
}
