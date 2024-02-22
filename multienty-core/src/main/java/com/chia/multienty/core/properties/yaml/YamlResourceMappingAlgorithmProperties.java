package com.chia.multienty.core.properties.yaml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YamlResourceMappingAlgorithmProperties {
    private String type;
    private Properties props = new Properties();
}
