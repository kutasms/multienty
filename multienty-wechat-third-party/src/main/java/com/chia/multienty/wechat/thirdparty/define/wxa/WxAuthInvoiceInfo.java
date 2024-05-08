package com.chia.multienty.wechat.thirdparty.define.wxa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 发票信息，如果是服务商代缴模式，不需要改参数
 */
@Data
public class WxAuthInvoiceInfo {
    /**
     * 发票类型 1: 不开发票 2: 电子发票 3: 增值税专票
     */
    @JsonProperty("invoice_type")
    private Integer invoiceType;
    /**
     * 发票类型=2时必填 电子发票开票信息
     */
    @JsonProperty("electronic")
    private ElectronicInvoiceInfo electronic;
    /**
     * 发票类型=3时必填 增值税专票开票信息
     */
    @JsonProperty("vat")
    private VATInvoiceInfo vat;
    /**
     * 发票抬头，需要和认证主体名称一样
     */
    @JsonProperty("invoice_title")
    private String invoiceTitle;
}
