package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class MppRegisterAuditEvent {
    @XmlElement(name = "AppId")
    private String thirdAppId;
    @XmlElement(name = "CreateTime")
    private Long createTime;
    @XmlElement(name = "InfoType")
    private String infoType;
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "status")
    private Integer status;
    @XmlElement(name = "auth_code")
    private String authCode;
    @XmlElement(name = "msg")
    private String msg;
    @XmlElement(name = "info")
    private MppRegisterAuditInfo info;

}
