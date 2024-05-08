package com.chia.multienty.wechat.official.domain.entity;

import com.chia.multienty.wechat.thirdparty.sdk.tools.CDataAdapter;
import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {})
public class OfficialReplyMessage {
    @XmlElement(name = "FromUserName")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String from;
    @XmlElement(name = "ToUserName")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String to;
    @XmlElement(name = "CreateTime")
    private Integer createTime;

    @XmlElement(name = "MsgType")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String msgType;
    @XmlElement(name = "Content")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String content;
}
