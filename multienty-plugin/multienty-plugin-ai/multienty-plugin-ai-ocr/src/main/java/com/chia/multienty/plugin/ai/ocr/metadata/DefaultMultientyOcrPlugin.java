package com.chia.multienty.plugin.ai.ocr.metadata;

import com.chia.multienty.plugin.ai.core.metadata.AbstractMultientyAIPlugin;
import com.chia.multienty.plugin.core.maintain.request.PluginInstallRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginUninstallRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginUpgradeRequest;
import com.chia.multienty.plugin.core.maintain.response.PluginInstallResponse;
import com.chia.multienty.plugin.core.maintain.response.PluginUninstallResponse;
import com.chia.multienty.plugin.core.maintain.response.PluginUpgradeResponse;

import java.util.Map;

public class DefaultMultientyOcrPlugin extends AbstractMultientyAIPlugin {

    private final String PLUGIN_NAME = "DEFAULT-AI-OCR";

    private final String PLUGIN_TYPE_NAME = "AI-OCR";

    @Override
    public String getName() {
        return PLUGIN_NAME;
    }

    @Override
    public String getTypeName() {
        return PLUGIN_TYPE_NAME;
    }

    @Override
    public boolean isOverrideNameOnRegister() {
        return false;
    }

    @Override
    public Map<String, String> getRequiredProperties() {
        return null;
    }


    @Override
    public PluginInstallResponse install(PluginInstallRequest request) {
        return null;
    }

    @Override
    public PluginUninstallResponse uninstall(PluginUninstallRequest request) {
        return null;
    }

    @Override
    public PluginUpgradeResponse upgrade(PluginUpgradeRequest request) {
        return null;
    }
}
