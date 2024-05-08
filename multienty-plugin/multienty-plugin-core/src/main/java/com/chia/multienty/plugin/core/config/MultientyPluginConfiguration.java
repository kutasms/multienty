package com.chia.multienty.plugin.core.config;

import com.chia.multienty.plugin.core.metadata.IMultientyPlugin;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class MultientyPluginConfiguration {
    private Map<String, IMultientyPlugin> plugins;
}
