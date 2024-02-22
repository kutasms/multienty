package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class BaseEvent {
    /**
     * 公众号微信号
     */
    @XmlElement(name = "ToUserName")
    private String to;
    /**
     * 	接收模板消息的用户的openid
     */
    @XmlElement(name = "FromUserName")
    private String from;
    /**
     * 创建时间
     */
    @XmlElement(name = "CreateTime")
    private Long createTime;
    /**
     * 	消息类型是事件
     */
    @XmlElement(name = "MsgType")
    private String msgType;
    @XmlElement(name = "Event")
    private String event;
}
