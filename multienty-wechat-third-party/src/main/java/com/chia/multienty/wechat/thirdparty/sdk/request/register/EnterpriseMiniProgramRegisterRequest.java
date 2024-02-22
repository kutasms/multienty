package com.chia.multienty.wechat.thirdparty.sdk.request.register;

import com.chia.multienty.wechat.thirdparty.define.register.EnterpriseCodeType;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.EnterpriseMiniProgramRegisterResponse;
import lombok.Data;

/**
 * 快速注册企业小程序
 */
@Data
@WxApi(url = MerchantApis.Register.REG_ENTERPRISE_MINI_PROGRAM)
public class EnterpriseMiniProgramRegisterRequest extends ComponentBaseRequest<EnterpriseMiniProgramRegisterResponse> {

    /**
     * 企业名（需与工商部门登记信息一致）；如果是“无主体名称个体工商户”则填“个体户+法人姓名”，例如“个体户张三”
     */
    private String name;
    /**
     * 企业代码
     */
    private String code;
    /**
     * 企业代码类型
     */
    private EnterpriseCodeType codeType;
    /**
     * 法人微信号
     */
    private String legalPersonaWechat;
    /**
     * 法人姓名（绑定银行卡）
     */
    private String legalPersonaName;
    /**
     * 第三方联系电话
     */
    private String componentPhone;
}
