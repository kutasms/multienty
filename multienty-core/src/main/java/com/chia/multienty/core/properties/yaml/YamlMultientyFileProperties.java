package com.chia.multienty.core.properties.yaml;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring.multienty.file")
public class YamlMultientyFileProperties implements YamlProperties {
    private String storageMode = FileStorageMode.LOCAL.name();

    private Map<String, String> custom;
    private Map<String, String> local;
    private Map<String, String> oss;
    private Map<String, String> obs;
    private Map<String, String> cos;
    public FileStorageMode getStorageModeEnum() {
        return FileStorageMode.valueOf(this.storageMode);
    }
}
