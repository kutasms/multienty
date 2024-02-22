package com.chia.multienty.core.registercenter.nacos;

import lombok.Data;

@Data
public class NacosExtensionConfig {
    private String dataId;
    private String group;
    private String refresh;
}
