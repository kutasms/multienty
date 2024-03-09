package com.chia.multienty.core.properties.yaml;

import lombok.Data;

@Data
public class YamlMultientyWechatProgramProperties {
    /**
     * 语言环境
     */
    private String lang = "zh-CN";

    /**
     * 正式版为 formal，体验版为 trial，开发版为 develop
     */
    private String envVersion = "develop";
    /**
     * 检查 page 是否存在，为 true 时 page 必须是已经发布的小程序存在的页面（否则报错）；
     * 为 false 时允许小程序未发布或者 page 不存在， 但 page 有数量上限（60000个）请勿滥用
     */
    private Boolean checkPath = false;
}
