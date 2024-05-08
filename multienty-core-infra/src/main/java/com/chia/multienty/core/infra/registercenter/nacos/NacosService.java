package com.chia.multienty.core.infra.registercenter.nacos;

import lombok.SneakyThrows;

import java.util.Map;

public interface NacosService {
    NacosConfig getConfig();

    Map<String, Object> getObject(String dataId);

    @SneakyThrows
    String getObjectStr(String dataId);

    @SneakyThrows
    String getObjectStr(String dataId, String group);

    String getString(String dataId);

    String getString(String dataId, String group);

    Map<String, Object> getObject(String dataId, String group);

    Map<String, Object> getAppObject();

    void updateYamlConfig(Object data, String dataId, String group);

    void updateYamlConfig(String data, String dataId, String group);

    @SneakyThrows
    void updateYamlConfig(String data, String dataId);

    void updateYamlConfig(Object data, String dataId);
}
