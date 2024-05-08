package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.mapper.SettingMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Setting;
import com.chia.multienty.core.service.SettingService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.SettingDetailGetParameter;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.base.SettingDeleteParameter;
import com.chia.multienty.core.parameter.base.SettingSaveParameter;
import com.chia.multienty.core.parameter.base.SettingUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * 系统配置信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class SettingServiceImpl extends KutaBaseServiceImpl<SettingMapper, Setting> implements SettingService {


    @Override
    public SettingDTO getDetail(SettingDetailGetParameter parameter) {
        return selectJoinOne(SettingDTO.class,
                        MPJWrappers.<Setting>lambdaJoin().eq(Setting::getSettingId, parameter.getSettingId()));
    }

    @Override
    public void delete(SettingDeleteParameter parameter) {
        removeByIdTE(parameter.getSettingId());
    }

    @Override
    public List<Setting> getSettings(Integer appId, Long owner) {
        return list(mtLambdaWrapper().eq(Setting::getAppId, appId).eq(Setting::getOwner, owner));
    }

    @Override
    public Setting getSetting(Integer appId, Long owner, String name) {
        return getOne(mtLambdaWrapper().eq(Setting::getAppId, appId).eq(Setting::getOwner, owner).eq(Setting::getName, name));
    }

    @Override
    public IPage<SettingDTO> getPage(SettingPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), SettingDTO.class,
                new MTLambdaWrapper<Setting>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getSettingIds()), Setting::getSettingId, parameter.getSettingIds())
        );
    }

    @Override
    public void save(SettingSaveParameter parameter) {
        Setting setting = new Setting();
        BeanUtils.copyProperties(parameter, setting);
        saveTE(setting);
        parameter.setSettingId(setting.getSettingId());
    }

    @Override
    public void update(SettingUpdateParameter parameter) {
        Setting setting = new Setting();
        BeanUtils.copyProperties(parameter, setting);
        updateByIdTE(setting);
    }
}
