package com.chia.multienty.wechat.thirdparty.define.wxa;

import com.chia.multienty.wechat.thirdparty.define.baseinfo.WxCustomerType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 微信认证数据
 */
@Data
public class WxAuthData {
    /**
     * 企业为1，个体工商户 为12，个人是15
     */
    private WxCustomerType customerType;
    /**
     * 认证任务id，打回重审调用reauth时为必填
     */
    private String taskId;
    /**
     * 	联系人信息
     */
    private WxAuthContactInfo contactInfo;
    /**
     * 发票信息，如果是服务商代缴模式，不需要改参数
     */
    private WxAuthInvoiceInfo invoiceInfo;
    /**
     * 非个人类型必填。主体资质材料 media_id 支持jpg,jpeg .bmp.gif .png格式，仅支持一张图片
     */
    private String qualification;
    /**
     * 主体资质其他证明材料 media_id 支持jpg,jpeg .bmp.gif .png格式，最多上传10张图片
     */
    private List<String> qualificationOther;
    /**
     * 小程序账号名称
     */
    private String accountName;
    /**
     * 小程序账号名称命名类型 1：基于自选词汇命名 2：基于商标命名
     */
    private Integer accountNameType;
    /**
     * 名称命中关键词-补充材料 media_id 支持jpg,jpeg .bmp.gif .png格式，支持上传多张图片
     */
    private List<String> accountSupplemental;
    /**
     * 支付方式 1：消耗服务商预购包 2：小程序开发者自行支付
     */
    private Integer payType;
    /**
     * 认证类型为个人类型时可以选择要认证的身份，从/wxa/sec/authidentitytree 里获取，填叶节点的name
     */
    private String authIdentification;
    /**
     * 	填了auth_identification则必填。身份证明材料 media_id
     * 	（1）基于不同认证身份上传不同的材料；
     * 	（2）认证类型=1时选填，支持上传10张图片
     * 	（3）支持jpg,jpeg .bmp.gif .png格式
     */
    @JsonProperty(value = "auth_ident_material")
    private List<String> authIdentityMaterial;
    /**
     * 第三方联系电话
     */
    private String thirdPartyPhone;
    /**
     * 选择服务商代缴模式时必填。服务市场appid，该服务市场账号主体必须与服务商账号主体一致
     */
    @JsonProperty(value = "service_appid")
    private String serviceAppId;
}
