package com.chia.multienty.core.registercenter.nacos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class NacosConfig {
    private String dataId;
    private String group;
    private List<NacosExtensionConfig> extensionConfigs;
}
