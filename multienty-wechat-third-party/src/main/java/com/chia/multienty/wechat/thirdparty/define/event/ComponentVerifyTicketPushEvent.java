package com.chia.multienty.wechat.thirdparty.define.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class ComponentVerifyTicketPushEvent {
    @JsonProperty(value = "AppId")
    @XmlElement(name = "AppId")
    private String appId;
    @JsonProperty(value = "CreateTime")
    @XmlElement(name = "CreateTime")
    private Long createTime;
    @JsonProperty(value = "InfoType")
    @XmlElement(name = "InfoType")
    private String infoType;
    @JsonProperty(value = "ComponentVerifyTicket")
    @XmlElement(name = "ComponentVerifyTicket")
    private String ticket;
}
