package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Setting;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.SettingDetailGetParameter;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.base.SettingDeleteParameter;
import com.chia.multienty.core.parameter.base.SettingSaveParameter;
import com.chia.multienty.core.parameter.base.SettingUpdateParameter;

import java.util.List;

/**
 * <p>
 * 系统配置信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface SettingService extends KutaBaseService<Setting> {

    SettingDTO getDetail(SettingDetailGetParameter parameter);

    Setting getSetting(Integer appId, Long owner, String name);

    IPage<SettingDTO> getPage(SettingPageGetParameter parameter);

    void save(SettingSaveParameter parameter);

    void update(SettingUpdateParameter parameter);

    void delete(SettingDeleteParameter parameter);

    List<Setting> getSettings(Integer appId, Long owner);

}
