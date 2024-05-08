package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.AppInstance;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.AppInstanceDTO;
import com.chia.multienty.core.parameter.tenant.AppInstanceDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.AppInstancePageGetParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceDeleteParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceEnableParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceDisableParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceSaveParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceUpdateParameter;
/**
 * <p>
 * 应用实例 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
public interface AppInstanceService extends KutaBaseService<AppInstance> {

    AppInstanceDTO getDetail(AppInstanceDetailGetParameter parameter);

    void delete(AppInstanceDeleteParameter parameter);

    IPage<AppInstanceDTO> getPage(AppInstancePageGetParameter parameter);
    void enable(AppInstanceEnableParameter parameter);

    void disable(AppInstanceDisableParameter parameter);

    void save(AppInstanceSaveParameter parameter);

    void update(AppInstanceUpdateParameter parameter);

}
