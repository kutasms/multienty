package com.chia.multienty.wechat.thirdparty.define.wxa;

import lombok.Data;

/**
 * 增值税发票开票信息
 */
@Data
public class VATInvoiceInfo {
    /**
     * 企业电话
     */
    private String enterprisePhone;
    /**
     * 纳税识别号（15位、17、18或20位）
     */
    private String id;
    /**
     * 企业注册地址
     */
    private String enterpriseAddress;
    /**
     * 企业开户银行
     */
    private String bankName;
    /**
     * 企业银行账号
     */
    private String bankAccount;
    /**
     * 发票邮寄地址邮编
     */
    private String mailingAddress;
    /**
     * 街道地址
     */
    private String address;
    /**
     * 联系人
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 县区
     */
    private String district;
    /**
     * 发票备注（选填）
     */
    private String desc;
}
