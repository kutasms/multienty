package com.chia.multienty.wechat.thirdparty.define.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 企业法人认证需要的信息
 */
@Data
public class EnterpriseVerifyInfo {
    /**
     * 企业名（需与工商部门登记信息一致)；如果是“无主体名称个体工商户”则填“个体户+法人姓名”，例如“个体户张三”
     */
    @JsonProperty("enterprise_name")
    private String enterpriseName;
    /**
     * 企业代码
     */
    @JsonProperty("code")
    private String code;
    /**
     * 企业代码类型 1：统一社会信用代码（18 位） 2：组织机构代码（9 位 xxxxxxxx-x） 3：营业执照注册号(15 位)
     */
    @JsonProperty("code_type")
    private EnterpriseCodeType codeType;
    /**
     * 法人微信号
     */
    @JsonProperty("legal_persona_wechat")
    private String legalPersonaWechat;
    /**
     * 法人姓名（绑定银行卡）
     */
    @JsonProperty("legal_persona_name")
    private String legalPersonaName;
    /**
     * 第三方联系电话
     */
    @JsonProperty("component_phone")
    private String componentPhone;
    /**
     * 法人身份证号
     */
    @JsonProperty("legal_persona_idcard")
    private String legalPersonaIdcard;
}
