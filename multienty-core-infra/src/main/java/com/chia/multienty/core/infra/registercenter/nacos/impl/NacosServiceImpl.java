package com.chia.multienty.core.infra.registercenter.nacos.impl;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.chia.multienty.core.properties.KutaNacosProperties;
import com.chia.multienty.core.infra.registercenter.nacos.NacosConfig;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.infra.registercenter.nacos.NacosService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NacosServiceImpl implements NacosService {
    private final KutaNacosProperties properties;

    private final ConfigService nacosConfigService;

    private final static String DEFAULT_GROUP = "DEFAULT_GROUP";
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 获取应用完整名称<br/>
     * 如：multienty-generator-dev
     * @return
     */
    private String getAppFullName() {
        return applicationName + SymbolEnum.HYPHEN.getCode() + active;
    }

    @Override
    public NacosConfig getConfig() {
        return new NacosConfig()
                .setDataId(getAppFullName())
                .setGroup(DEFAULT_GROUP)
                .setExtensionConfigs(Arrays.asList(properties.getExtensionConfigs()));
    }

    @Override
    @SneakyThrows
    public Map<String, Object> getObject(String dataId) {
        String origRsp = nacosConfigService.getConfig(dataId, DEFAULT_GROUP, 5000);
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.loadAs(origRsp, Map.class);
        return map;
    }

    @Override
    @SneakyThrows
    public String getObjectStr(String dataId) {
        return nacosConfigService.getConfig(dataId, DEFAULT_GROUP, 5000);
    }

    @Override
    @SneakyThrows
    public String getObjectStr(String dataId, String group) {
        return nacosConfigService.getConfig(dataId, group, 5000);
    }

    @Override
    @SneakyThrows
    public String getString(String dataId) {
        return nacosConfigService.getConfig(dataId, DEFAULT_GROUP, 5000);
    }

    @Override
    @SneakyThrows
    public String getString(String dataId, String group) {
        return nacosConfigService.getConfig(dataId, group, 5000);
    }


    @Override
    @SneakyThrows
    public Map<String, Object> getObject(String dataId, String group) {
        String origRsp = nacosConfigService.getConfig(dataId, group, 5000);
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.loadAs(origRsp, Map.class);
        return map;
    }

    @SneakyThrows
    @Override
    public Map<String, Object> getAppObject() {
        String origRsp = nacosConfigService.getConfig(getAppFullName(), DEFAULT_GROUP, 5000);
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.loadAs(origRsp, Map.class);
        return map;
    }

    @SneakyThrows
    @Override
    public void updateYamlConfig(Object data, String dataId, String group) {
        Yaml yaml = new Yaml();
        String dump = yaml.dumpAs(data, Tag.MAP, DumperOptions.FlowStyle.BLOCK);

        nacosConfigService.publishConfig(dataId, group, dump, ConfigType.YAML.getType());
    }

    @Override
    @SneakyThrows
    public void updateYamlConfig(String data, String dataId, String group) {
        nacosConfigService.publishConfig(dataId, group, data, ConfigType.YAML.getType());
    }

    @Override
    @SneakyThrows
    public void updateYamlConfig(String data, String dataId) {
        nacosConfigService.publishConfig(dataId, DEFAULT_GROUP, data, ConfigType.YAML.getType());
    }

    @SneakyThrows
    @Override
    public void updateYamlConfig(Object data, String dataId) {
        Yaml yaml = new Yaml();
        String dump = yaml.dumpAs(data, Tag.MAP, DumperOptions.FlowStyle.BLOCK);

        nacosConfigService.publishConfig(dataId, DEFAULT_GROUP, dump, ConfigType.YAML.getType());
    }
}
