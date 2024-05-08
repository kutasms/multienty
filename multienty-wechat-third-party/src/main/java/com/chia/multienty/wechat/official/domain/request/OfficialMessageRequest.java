package com.chia.multienty.wechat.official.domain.request;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class OfficialMessageRequest {
    @XmlElement(name = "FromUserName")
    private String from;
    @XmlElement(name = "ToUserName")
    private String to;
    @XmlElement(name = "CreateTime")
    private String createTime;

    @XmlElement(name = "MsgType")
    private String msgType;
    @XmlElement(name = "Content")
    private String content;
    @XmlElement(name = "MsgId")
    private String msgId;
}
