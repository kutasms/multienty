package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.SecretAuth;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.SecretAuthDTO;
import com.chia.multienty.core.parameter.tenant.SecretAuthDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthPageGetParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthDeleteParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthEnableParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthDisableParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthSaveParameter;
import com.chia.multienty.core.parameter.tenant.SecretAuthUpdateParameter;
/**
 * <p>
 * 密钥授权 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
public interface SecretAuthService extends KutaBaseService<SecretAuth> {

    SecretAuthDTO getDetail(SecretAuthDetailGetParameter parameter);

    void delete(SecretAuthDeleteParameter parameter);

    IPage<SecretAuthDTO> getPage(SecretAuthPageGetParameter parameter);
    void enable(SecretAuthEnableParameter parameter);

    void disable(SecretAuthDisableParameter parameter);

    void save(SecretAuthSaveParameter parameter);

    void update(SecretAuthUpdateParameter parameter);

}
