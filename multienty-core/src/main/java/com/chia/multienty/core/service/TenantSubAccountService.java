package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.TenantSubAccountDTO;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.pojo.TenantSubAccount;

/**
 * <p>
 * 余额账单 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
public interface TenantSubAccountService extends KutaBaseService<TenantSubAccount>,MultientyUserService  {

    LoggedUserVO getLoggedUser(Long userId);

    TenantSubAccountDTO getDetail(TenantSubAccountDetailGetParameter parameter);

    void delete(TenantSubAccountDeleteParameter parameter);

    IPage<TenantSubAccountDTO> getPage(TenantSubAccountPageGetParameter parameter);
    void enable(TenantSubAccountEnableParameter parameter);

    void disable(TenantSubAccountDisableParameter parameter);

    void save(TenantSubAccountSaveParameter parameter);

    void update(TenantSubAccountUpdateParameter parameter);

}
