package com.chia.multienty.core.fusion.registercenter.nacos;

import lombok.SneakyThrows;

import java.util.Map;

public interface NacosService {
    NacosConfig getConfig();

    @SneakyThrows
    Map<String, Object> getObject(String dataId, String group);

    @SneakyThrows
    Map<String, Object> getAppObject();

    @SneakyThrows
    void updateYamlConfig(Object data, String dataId, String group);
}
