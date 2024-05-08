package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

/**
 * 试用小程序注册事件
 */
@Data
public class BetaMppRegisterEvent {
    @XmlElement(name = "AppId")
    private String componentAppId;
    @XmlElement(name = "CreateTime")
    private String createTime;
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "status")
    private Integer status;
    @XmlElement(name = "msg")
    private String msg;
    @XmlElement(name = "info")
    private BetaMppRegisterInfo info;
}
