package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class AuthorizationUpdateEvent {
    @XmlElement(name = "AppId")
    private String appId;
    @XmlElement(name = "CreateTime")
    private Long createTime;
    @XmlElement(name = "InfoType")
    private String infoType;
    @XmlElement(name = "AuthorizerAppid")
    private String authorizerAppId;
    @XmlElement(name = "AuthorizationCode")
    private String authorizationCode;
    @XmlElement(name = "AuthorizationCodeExpiredTime")
    private Long authorizationCodeExpiredTime;
    @XmlElement(name = "PreAuthCode")
    private String preAuthCode;
}
