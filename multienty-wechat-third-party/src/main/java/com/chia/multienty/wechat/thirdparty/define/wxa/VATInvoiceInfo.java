package com.chia.multienty.wechat.thirdparty.define.wxa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 增值税发票开票信息
 */
@Data
public class VATInvoiceInfo {
    /**
     * 企业电话
     */
    @JsonProperty("enterprise_phone")
    private String enterprisePhone;
    /**
     * 纳税识别号（15位、17、18或20位）
     */
    @JsonProperty("id")
    private String id;
    /**
     * 企业注册地址
     */
    @JsonProperty("enterprise_address")
    private String enterpriseAddress;
    /**
     * 企业开户银行
     */
    @JsonProperty("bank_name")
    private String bankName;
    /**
     * 企业银行账号
     */
    @JsonProperty("bank_account")
    private String bankAccount;
    /**
     * 发票邮寄地址邮编
     */
    @JsonProperty("mailing_address")
    private String mailingAddress;
    /**
     * 街道地址
     */
    @JsonProperty("address")
    private String address;
    /**
     * 联系人
     */
    @JsonProperty("name")
    private String name;
    /**
     * 联系电话
     */
    @JsonProperty("phone")
    private String phone;
    /**
     * 省份
     */
    @JsonProperty("province")
    private String province;
    /**
     * 城市
     */
    @JsonProperty("city")
    private String city;
    /**
     * 县区
     */
    @JsonProperty("district")
    private String district;
    /**
     * 发票备注（选填）
     */
    @JsonProperty("desc")
    private String desc;
}
