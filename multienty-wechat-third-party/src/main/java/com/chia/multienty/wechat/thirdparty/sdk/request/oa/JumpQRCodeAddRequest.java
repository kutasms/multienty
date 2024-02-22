package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.JumpQRCodeAddResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 增加或修改二维码规则
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.ADD_JUMP_QRCODE)
public class JumpQRCodeAddRequest extends AuthorizerBaseRequest<JumpQRCodeAddResponse> {
    /**
     * 编辑标志位，0 表示新增二维码规则，1 表示修改已有二维码规则
     */
    @JsonProperty(value = "is_edit")
    private Integer isEdit;
    /**
     * 二维码规则
     */
    private String prefix;
    /**
     * 小程序功能页面
     */
    private String path;
    /**
     * 测试范围。
     * 1表示开发版（配置只对开发者生效）；
     * 2表示体验版（配置对管理员、体验者生效)；
     * 3表示正式版（配置对开发者、管理员和体验者生效）。
     * 增加或修改“扫普通二维码打开小程序”的二维码规则才需要传这个参数
     */
    private Integer openVersion;
    /**
     * 测试链接，至多 5 个用于测试的二维码完整链接，此链接必须符合已填写的二维码规则。
     * 增加或修改“扫普通二维码打开小程序”的二维码规则才需要传这个参数
     */
    private List<String> debugUrl;
    /**
     * 是否独占符合二维码前缀匹配规则的所有子规 1 为不占用，2 为占用。查看详细规则
     * 增加或修改“扫普通二维码打开小程序”的二维码规则才需要传这个参数
     */
    private Integer permitSubRule;
    /**
     * 扫了服务号二维码之后要跳转的小程序的appid。
     * 增加或修改“扫服务号二维码打开小程序”的二维码规则才需要传这个参数
     */
    @JsonProperty(value = "appid")
    private String appId;
}
