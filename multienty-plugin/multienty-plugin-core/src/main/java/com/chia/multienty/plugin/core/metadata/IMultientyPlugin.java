package com.chia.multienty.plugin.core.metadata;

import com.chia.multienty.plugin.core.maintain.request.PluginInstallRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginUninstallRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginUpgradeRequest;
import com.chia.multienty.plugin.core.maintain.response.PluginInstallResponse;
import com.chia.multienty.plugin.core.maintain.response.PluginUninstallResponse;
import com.chia.multienty.plugin.core.maintain.response.PluginUpgradeResponse;

import java.util.Map;

/**
 * Multienty标准插件接口
 */
public interface IMultientyPlugin {
    /**
     * 获取插件名称
     * @return 插件名称
     */
    String getName();

    /**
     * 获取插件类型名称
     * @return
     */
    String getTypeName();

     boolean isOverrideNameOnRegister();

    /**
     * 获取必须配置的属性
     * <p>key: property key, value: describe</p>
     * @return
     */
    Map<String, String> getRequiredProperties();

    /**
     * 安装插件
     * @param request 插件安装请求
     * @return
     */
    PluginInstallResponse install(PluginInstallRequest request);

    /**
     * 卸载插件
     * @param request 插件卸载请求
     * @return
     */
    PluginUninstallResponse uninstall(PluginUninstallRequest request);

    /**
     * 升级插件
     * @param request 插件升级请求
     * @return
     */
    PluginUpgradeResponse upgrade(PluginUpgradeRequest request);
}
