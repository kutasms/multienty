package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class MppAuthenticationTaskEvent {
    @XmlElement(name = "InfoType")
    private String infoType;
    @XmlElement(name = "appid")
    private String appId;
    @XmlElement(name = "task_status")
    private Integer taskStatus;
    @XmlElement(name = "apply_status")
    private Integer applyStatus;
    @XmlElement(name = "dispatch_info.provider")
    private String dispatchInfoProvider;
    @XmlElement(name = "dispatch_info.contact")
    private String dispatchInfoContact;
    @XmlElement(name = "dispatch_info.dispatch_time")
    private Integer dispatchTime;
    @XmlElement(name = "message")
    private String message;
}
