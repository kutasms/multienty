package com.chia.multienty.core.dubbo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.pojo.Setting;
import com.chia.multienty.core.pojo.WebLog;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;

import java.util.List;

public interface DubboMultientyService {
    boolean saveWebLog(WebLog webLog);
    WechatAppDTO getWechatApp(WechatAppDetailGetParameter parameter);
    TenantDTO getTenant(TenantDetailGetParameter parameter);
    IPage<RabbitLogDTO> getRabbitLogPage(RabbitLogPageGetParameter parameter);
    IPage<SettingDTO> getSettingPage(SettingPageGetParameter parameter);
    List<Setting> getSettings(Integer appId, Long owner);
    Setting getSetting(Integer appId, Long owner, String name);
}
