package com.chia.multienty.plugin.core.service;

import com.chia.multienty.plugin.core.maintain.request.PluginClassPathUpdateRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginInstallRequest;
import com.chia.multienty.plugin.core.maintain.response.PluginInstallResponse;

public interface PluginMaintainService {
    PluginInstallResponse install(PluginInstallRequest request);

    void setClassPath(PluginClassPathUpdateRequest request);
}
