package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.App;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.AppDTO;
import com.chia.multienty.core.parameter.base.AppDetailGetParameter;
import com.chia.multienty.core.parameter.base.AppPageGetParameter;
import com.chia.multienty.core.parameter.base.AppDeleteParameter;
import com.chia.multienty.core.parameter.base.AppEnableParameter;
import com.chia.multienty.core.parameter.base.AppDisableParameter;
import com.chia.multienty.core.parameter.base.AppSaveParameter;
import com.chia.multienty.core.parameter.base.AppUpdateParameter;
/**
 * <p>
 * 应用 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
public interface AppService extends KutaBaseService<App> {

    AppDTO getDetail(AppDetailGetParameter parameter);

    void delete(AppDeleteParameter parameter);

    IPage<AppDTO> getPage(AppPageGetParameter parameter);
    void enable(AppEnableParameter parameter);

    void disable(AppDisableParameter parameter);

    void save(AppSaveParameter parameter);

    void update(AppUpdateParameter parameter);

}
