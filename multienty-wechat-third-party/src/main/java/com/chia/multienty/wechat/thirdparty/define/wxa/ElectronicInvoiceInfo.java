package com.chia.multienty.wechat.thirdparty.define.wxa;

import lombok.Data;

/**
 * 电子发票开票信息
 */
@Data
public class ElectronicInvoiceInfo {
    /**
     * 纳税识别号（15位、17、18或20位）
     */
    private String id;
    /**
     * 发票备注（选填）
     */
    private String desc;
}
