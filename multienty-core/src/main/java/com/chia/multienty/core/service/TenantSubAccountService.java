package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.TenantSubAccountDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.TenantSubAccount;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.user.TenantSubAccountDetailGetParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountPageGetParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountDeleteParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountEnableParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountDisableParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountSaveParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountUpdateParameter;
/**
 * <p>
 * 余额账单 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
public interface TenantSubAccountService extends KutaBaseService<TenantSubAccount> {

    TenantSubAccountDTO getDetail(TenantSubAccountDetailGetParameter parameter);

    void delete(TenantSubAccountDeleteParameter parameter);

    IPage<TenantSubAccountDTO> getPage(TenantSubAccountPageGetParameter parameter);
    void enable(TenantSubAccountEnableParameter parameter);

    void disable(TenantSubAccountDisableParameter parameter);

    void save(TenantSubAccountSaveParameter parameter);

    void update(TenantSubAccountUpdateParameter parameter);

}
