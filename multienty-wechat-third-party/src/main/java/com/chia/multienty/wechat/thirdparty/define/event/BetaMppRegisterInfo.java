package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class BetaMppRegisterInfo {
    /**
     * 唯一编号
     */
    @XmlElement(name = "unique_id")
    private String uniqueId;
    /**
     * 小程序名称
     */
    @XmlElement(name = "name")
    private String name;
}
