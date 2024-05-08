package com.chia.multienty.wechat.thirdparty.define.event;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class MppRegisterAuditInfo {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "code")
    private String code;
    @XmlElement(name = "code_type")
    private String codeType;
    @XmlElement(name = "legal_persona_wechat")
    private String legalPersonaWechat;
    @XmlElement(name = "legal_persona_name")
    private String legalPersonaName;
    @XmlElement(name = "component_phone")
    private String componentPhone;

}
