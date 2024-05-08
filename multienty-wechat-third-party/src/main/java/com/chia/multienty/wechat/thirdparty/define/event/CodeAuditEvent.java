package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class CodeAuditEvent extends BaseEvent{
    @XmlElement(name = "SuccTime")
    private Long successTime;
    @XmlElement(name = "FailTime")
    private Long failTime;
    @XmlElement(name = "DelayTime")
    private Long delayTime;
    @XmlElement(name = "Reason")
    private String reason;
    @XmlElement(name = "ScreenShot")
    private String screenShot;
}
