package com.chia.multienty.plugin.core.service.impl;

import com.chia.multienty.core.domain.dto.PluginDTO;
import com.chia.multienty.core.domain.dto.PluginResDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.infra.registercenter.nacos.NacosService;
import com.chia.multienty.core.parameter.plugin.PluginContractSaveParameter;
import com.chia.multienty.core.parameter.plugin.PluginDetailGetParameter;
import com.chia.multienty.core.service.PluginContractService;
import com.chia.multienty.core.service.PluginService;
import com.chia.multienty.core.util.AssertUtil;
import com.chia.multienty.plugin.core.maintain.request.PluginClassPathUpdateRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginInstallRequest;
import com.chia.multienty.plugin.core.maintain.response.PluginInstallResponse;
import com.chia.multienty.plugin.core.metadata.IMultientyPlugin;
import com.chia.multienty.plugin.core.metadata.PluginSource;
import com.chia.multienty.plugin.core.properties.YamlMultientyPluginProperties;
import com.chia.multienty.plugin.core.service.PluginMaintainService;
import com.chia.multienty.plugin.core.util.PluginUtil;
import com.chia.multienty.plugin.core.util.ZipUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PluginMaintainServiceImpl implements PluginMaintainService {

    private final PluginContractService pluginContractService;

    private final PluginService pluginService;

    private final YamlMultientyPluginProperties properties;

    private final NacosService nacosService;

    private final String MULTIENTY_DATA_ID = "multienty.yml";




    @Override
    @SneakyThrows
    public PluginInstallResponse install(PluginInstallRequest request) {
        // 检测参数
        checkArgs(request);
        LocalDateTime deadline = null;
        PluginDTO plugin = null;

        switch (request.getSource()) {
            case MARKETING:
                // TODO: 1. 购买 下单->支付 2. 通过accessToken + tradeNo 获取刚才支付的插件信息
                break;
            case SELF:
                plugin = pluginService.getDetail(new PluginDetailGetParameter()
                        .setPluginId(request.getPluginId())
                        .setContainsRes(true)
                        .setContainsDependency(true));
                break;
        }
        // 保存插件合约
        pluginContractService.save(new PluginContractSaveParameter()
                .setPluginId(request.getPluginId())
                .setDeadline(null)
        );

        // 复制jar包
        switch (request.getSource()) {
            case MARKETING:
                // TODO: 从远程下载jar包, 然后保存至classpath
                break;
            case SELF:
                for (PluginResDTO res : plugin.getResList()) {
                    copyRes(res.getResUrl());
                }
                break;
        }

        // 热启动插件
        IMultientyPlugin instance = PluginUtil.registerPlugin(
                plugin.getLibName(), plugin.getPackageName(), plugin.getClassName());

        // TODO: 安装vue插件
        return PluginInstallResponse.ok();
    }

    @Override
    public void setClassPath(PluginClassPathUpdateRequest request) {
        Map<String, Object> map = nacosService.getObject(MULTIENTY_DATA_ID);
        Map<String, Object> pluginMap = (Map<String, Object>)map.get("plugin");
        pluginMap.put("class-path", request.getClassPath());
        nacosService.updateYamlConfig(map, MULTIENTY_DATA_ID);
    }

    @SneakyThrows
    public void copyRes(String jarPath) {
        AssertUtil.checkNull(properties.getClassPath(), "plugin.properties.class-path");
        File srcFile = new File(jarPath);
        if(!srcFile.exists()) {
            throw new KutaRuntimeException(HttpResultEnum.FILE_NOT_EXISTS);
        }
        File descDir = new File(properties.getClassPath());
        if(descDir.isDirectory() && !descDir.exists()) {
            boolean created = descDir.mkdirs();
            if(!created) {
                throw new KutaRuntimeException(HttpResultEnum.DIRECTORY_MAKE_FAILURE);
            }
        }
        FileUtils.copyFileToDirectory(srcFile, descDir, true);
        tryUnzip(descDir, srcFile.getName());
    }

    public void tryUnzip(File fileDir, String fileName) {
        if(!fileName.endsWith(".zip")) {
            return;
        }
        ZipUtil.unzip(Paths.get(fileDir.getPath(), fileName).toUri().getPath(),
                fileDir.getPath());
    }

    public void checkArgs(PluginInstallRequest request) {
        AssertUtil.checkNull(request.getPluginId(), "plugin-id");
        if(request.getSource().equals(PluginSource.MARKETING)) {
            AssertUtil.checkNull(request.getAppId(), "appId");
            AssertUtil.checkNull(request.getAppSecret(), "appSecret");
            // TODO: 如果从服务市场获取插件，则需要根据appId和appSecret获取服务市场的accessToken
        }
        else if(request.getSource().equals(PluginSource.SELF)) {
            // 自研插件

        }
    }
}
