package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.WechatAppFuncScopeDTO;
import com.chia.multienty.core.mapper.WechatAppFuncScopeMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatAppFuncScope;
import com.chia.multienty.core.service.WechatAppFuncScopeService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 权限集 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */
@Service
public class WechatAppFuncScopeServiceImpl extends KutaBaseServiceImpl<WechatAppFuncScopeMapper, WechatAppFuncScope> implements WechatAppFuncScopeService {


    @Override
    public WechatAppFuncScopeDTO getDetail(WechatAppFuncScopeDetailGetParameter parameter) {
        return selectJoinOne(WechatAppFuncScopeDTO.class,
                        MPJWrappers.<WechatAppFuncScope>lambdaJoin().eq(WechatAppFuncScope::getFsId, parameter.getFsId()));
    }

    @Override
    public void delete(WechatAppFuncScopeDeleteParameter parameter) {
        removeByIdTE(parameter.getFsId());
    }

    @Override
    public IPage<WechatAppFuncScopeDTO> getPage(WechatAppFuncScopePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatAppFuncScopeDTO.class,
                new MTLambdaWrapper<WechatAppFuncScope>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getFsIds()), WechatAppFuncScope::getFsId, parameter.getFsIds())
        );
    }

    @Override
    public boolean deleteByProgramId(Long programId) {
        return remove(new LambdaQueryWrapper<WechatAppFuncScope>().eq(WechatAppFuncScope::getProgramId, programId));
    }

    @Override
    public void save(WechatAppFuncScopeSaveParameter parameter) {
        WechatAppFuncScope wechatAppFuncScope = new WechatAppFuncScope();
        BeanUtils.copyProperties(parameter, wechatAppFuncScope);
        saveTE(wechatAppFuncScope);
        parameter.setFsId(wechatAppFuncScope.getFsId());
        parameter.setProgramId(wechatAppFuncScope.getProgramId());
    }

    @Override
    public void update(WechatAppFuncScopeUpdateParameter parameter) {
        WechatAppFuncScope wechatAppFuncScope = new WechatAppFuncScope();
        BeanUtils.copyProperties(parameter, wechatAppFuncScope);
        updateByIdTE(wechatAppFuncScope);
    }
}
