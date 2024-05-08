package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.TenantSecret;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.TenantSecretDTO;
import com.chia.multienty.core.parameter.tenant.TenantSecretDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantSecretPageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantSecretDeleteParameter;
import com.chia.multienty.core.parameter.tenant.TenantSecretEnableParameter;
import com.chia.multienty.core.parameter.tenant.TenantSecretDisableParameter;
import com.chia.multienty.core.parameter.tenant.TenantSecretSaveParameter;
import com.chia.multienty.core.parameter.tenant.TenantSecretUpdateParameter;
/**
 * <p>
 * 租户密钥 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
public interface TenantSecretService extends KutaBaseService<TenantSecret> {

    TenantSecretDTO getDetail(TenantSecretDetailGetParameter parameter);

    void delete(TenantSecretDeleteParameter parameter);

    IPage<TenantSecretDTO> getPage(TenantSecretPageGetParameter parameter);
    void enable(TenantSecretEnableParameter parameter);

    void disable(TenantSecretDisableParameter parameter);

    void save(TenantSecretSaveParameter parameter);

    void update(TenantSecretUpdateParameter parameter);

}
