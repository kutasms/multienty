package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class NickNameSetEvent extends BaseEvent{

    @XmlElement(name = "nickname")
    private String nickName;
    @XmlElement(name = "ret")
    private String ret;
    @XmlElement(name = "reason")
    private String reason;
}
