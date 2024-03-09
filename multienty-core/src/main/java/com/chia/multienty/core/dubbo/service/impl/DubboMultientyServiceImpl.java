package com.chia.multienty.core.dubbo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.pojo.Setting;
import com.chia.multienty.core.pojo.WebLog;
import com.chia.multienty.core.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@DubboService
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class DubboMultientyServiceImpl implements DubboMultientyService {
    private final WebLogService webLogService;
    private final TenantService tenantService;
    private final WechatAppService wechatAppService;
    private final RabbitLogService rabbitLogService;
    private final SettingService settingService;
    @Override
    public boolean saveWebLog(WebLog webLog) {
        return webLogService.save(webLog);
    }

    @Override
    public WechatAppDTO getWechatApp(WechatAppDetailGetParameter parameter) {
        return wechatAppService.getDetail(parameter);
    }

    @Override
    public TenantDTO getTenant(TenantDetailGetParameter parameter) {
        return tenantService.getDetail(parameter);
    }

    @Override
    public IPage<RabbitLogDTO> getRabbitLogPage(RabbitLogPageGetParameter parameter) {
        return rabbitLogService.getPage(parameter);
    }

    @Override
    public IPage<SettingDTO> getSettingPage(SettingPageGetParameter parameter) {
        return settingService.getPage(parameter);
    }

    @Override
    public List<Setting> getSettings(Integer appId, Long owner) {
        return settingService.getSettings(appId, owner);
    }

    @Override
    public Setting getSetting(Integer appId, Long owner, String name) {
        return settingService.getSetting(appId, owner, name);
    }
}
