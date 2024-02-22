package com.chia.multienty.core.properties.yaml;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YamlMultiTenantAlgorithmProperties implements YamlProperties {
    private Map<String, YamlResourceMappingAlgorithmProperties> resourceMapping;
}
