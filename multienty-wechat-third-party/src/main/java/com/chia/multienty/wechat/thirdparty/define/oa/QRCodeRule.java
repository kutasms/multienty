package com.chia.multienty.wechat.thirdparty.define.oa;

import lombok.Data;

import java.util.List;

/**
 * 二维码规则
 */
@Data
public class QRCodeRule {
    /**
     * 二维码规则，说明，服务号二维码规则已过滤不展示
     */
    private String prefix;
    /**
     * 小程序功能页面
     */
    private String path;
    /**
     * 发布标志位，1 表示未发布，2 表示已发布
     */
    private Integer state;
    /**
     * 测试范围。
     * 获取“扫普通二维码打开小程序”已设置的二维码规则才返回这个参数。
     * 1表示开发版（配置只对开发者生效）；
     * 2表示体验版（配置对管理员、体验者生效)；
     * 3表示正式版（配置对开发者、管理员和体验者生效）。
     * 特别说明，不管普通二维码规则是否发布，
     * 开发者/体验者/管理员在扫描 debug_url 所对应的二维码时，
     * 都会根据 open_version 来决定打开开发版、体验版或正式版
     */
    private Integer openVersion;
    /**
     * 测试链接（选填）可填写不多于 5 个用于测试的二维码完整链接，
     * 此链接必须符合已填写的二维码规则。获取“扫普通二维码打开小程序”
     * 已设置的二维码规则才返回这个参数。
     */
    private List<String> debugUrl;
}
