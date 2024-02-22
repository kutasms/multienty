package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatAppFuncScopeDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WechatAppFuncScope;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeUpdateParameter;
/**
 * <p>
 * 权限集 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */
public interface WechatAppFuncScopeService extends KutaBaseService<WechatAppFuncScope> {

    WechatAppFuncScopeDTO getDetail(WechatAppFuncScopeDetailGetParameter parameter);

    void delete(WechatAppFuncScopeDeleteParameter parameter);


    IPage<WechatAppFuncScopeDTO> getPage(WechatAppFuncScopePageGetParameter parameter);

    boolean deleteByProgramId(Long programId);

    void save(WechatAppFuncScopeSaveParameter parameter);

    void update(WechatAppFuncScopeUpdateParameter parameter);

}
