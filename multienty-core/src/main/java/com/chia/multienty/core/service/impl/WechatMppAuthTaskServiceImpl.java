package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.WechatMppAuthTask;
import com.chia.multienty.core.mapper.WechatMppAuthTaskMapper;
import com.chia.multienty.core.service.WechatMppAuthTaskService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatMppAuthTaskDTO;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.tools.MultientyContext;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.chia.multienty.core.tools.IdWorkerProvider;
/**
 * <p>
 * 微信小程序认证任务 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatMppAuthTaskServiceImpl extends KutaBaseServiceImpl<WechatMppAuthTaskMapper, WechatMppAuthTask> implements WechatMppAuthTaskService {


    @Override
    public WechatMppAuthTaskDTO getDetail(WechatMppAuthTaskDetailGetParameter parameter) {
        return selectJoinOne(WechatMppAuthTaskDTO.class,
                        MPJWrappers.<WechatMppAuthTask>lambdaJoin()
                        .eq(WechatMppAuthTask::getTenantId, parameter.getTenantId())
                        .eq(WechatMppAuthTask::getTaskId, parameter.getTaskId()));
    }

    @Override
    public void update(WechatMppAuthTaskUpdateParameter parameter) {
        WechatMppAuthTask wechatMppAuthTask = new WechatMppAuthTask();
        BeanUtils.copyProperties(parameter, wechatMppAuthTask);
        update(wechatMppAuthTask, new LambdaQueryWrapper<WechatMppAuthTask>()
                .eq(WechatMppAuthTask::getTenantId, parameter.getTenantId())
                .eq(WechatMppAuthTask::getTaskId, parameter.getTaskId()));
    }

    @Override
    public void delete(WechatMppAuthTaskDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatMppAuthTask>()
                .eq(WechatMppAuthTask::getTenantId, parameter.getTenantId())
                .eq(WechatMppAuthTask::getTaskId, parameter.getTaskId()));
    }

    @Override
    public WechatMppAuthTask getBy(String appId) {
        return getOne(mtLambdaWrapper().eq(WechatMppAuthTask::getAppId, appId));
    }

    @Override
    public WechatMppAuthTask getBy(String wxTaskId, String tenantId) {
        return getOne(mtLambdaWrapper()
                .eq(WechatMppAuthTask::getTaskId, wxTaskId)
                .eq(WechatMppAuthTask::getTenantId, tenantId)
        );
    }

    @Override
    public IPage<WechatMppAuthTaskDTO> getPage(WechatMppAuthTaskPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppAuthTaskDTO.class,
                new MTLambdaWrapper<WechatMppAuthTask>()
                        .solveGenericParameters(parameter)
                        .eq(WechatMppAuthTask::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getTaskIds()),
                                WechatMppAuthTask::getTaskId,
                                parameter.getTaskIds())
        );
    }

    @Override
    public void save(WechatMppAuthTaskSaveParameter parameter) {
        WechatMppAuthTask wechatMppAuthTask = new WechatMppAuthTask();
        BeanUtils.copyProperties(parameter, wechatMppAuthTask);
        wechatMppAuthTask.setTaskId(IdWorkerProvider.next());

        wechatMppAuthTask.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatMppAuthTask);
        parameter.setTaskId(wechatMppAuthTask.getTaskId());
    }


    @Override
    public void enable(WechatMppAuthTaskEnableParameter parameter) {
        WechatMppAuthTask wechatMppAuthTask = new WechatMppAuthTask();
        BeanUtils.copyProperties(parameter, wechatMppAuthTask);
        wechatMppAuthTask.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatMppAuthTask, new LambdaQueryWrapper<WechatMppAuthTask>()
                .eq(WechatMppAuthTask::getTenantId, parameter.getTenantId())
                .eq(WechatMppAuthTask::getTaskId, parameter.getTaskId()));

    }

    @Override
    public void disable(WechatMppAuthTaskDisableParameter parameter) {
        WechatMppAuthTask wechatMppAuthTask = new WechatMppAuthTask();
        BeanUtils.copyProperties(parameter, wechatMppAuthTask);
        wechatMppAuthTask.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatMppAuthTask, new LambdaQueryWrapper<WechatMppAuthTask>()
                .eq(WechatMppAuthTask::getTenantId, parameter.getTenantId())
                .eq(WechatMppAuthTask::getTaskId, parameter.getTaskId()));
    }
}
