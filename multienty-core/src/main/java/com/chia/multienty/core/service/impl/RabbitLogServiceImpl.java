package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.RabbitLogMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.RabbitLog;
import com.chia.multienty.core.service.RabbitLogService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.log.RabbitLogDetailGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogDeleteParameter;
import com.chia.multienty.core.parameter.log.RabbitLogSaveParameter;
import com.chia.multienty.core.parameter.log.RabbitLogUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * Rabbit MQ日志信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class RabbitLogServiceImpl extends KutaBaseServiceImpl<RabbitLogMapper, RabbitLog> implements RabbitLogService {


    @Override
    public RabbitLog getByKey(String key) {
        return baseMapper.selectOne(new LambdaQueryWrapper<RabbitLog>().eq(RabbitLog::getKey, key));
    }

    @Override
    public boolean remove(String routingKey, Long metaId, String boType) {
        RabbitLog rabbitLog = new RabbitLog();
        rabbitLog.setStatus(StatusEnum.DELETED.getCode());
        return update(rabbitLog,
                new LambdaUpdateWrapper<RabbitLog>()
                        .eq(RabbitLog::getMetaId, metaId)
                        .eq(RabbitLog::getRoutingKey, routingKey)
                        .eq(RabbitLog::getBoType, boType)
        );
    }

    @Override
    public void finish(String key) {
        RabbitLog rabbitLog = baseMapper.selectOne(
                new LambdaQueryWrapper<RabbitLog>()
                        .select(RabbitLog::getRid, RabbitLog::getVersion)
                        .eq(RabbitLog::getKey, key)
        );
        if(rabbitLog!=null && rabbitLog.getRid() != null) {
            rabbitLog.setStatus(StatusEnum.COMPLETED.getCode());
            updateByIdTE(rabbitLog);
        }
    }

    @Override
    public RabbitLogDTO getDetail(RabbitLogDetailGetParameter parameter) {
        return selectJoinOne(RabbitLogDTO.class,
                        MPJWrappers.<RabbitLog>lambdaJoin().eq(RabbitLog::getRid, parameter.getRid()));
    }

    @Override
    public void delete(RabbitLogDeleteParameter parameter) {
        removeByIdTE(parameter.getRid());
    }

    @Override
    public IPage<RabbitLogDTO> getPage(RabbitLogPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), RabbitLogDTO.class,
                new MTLambdaWrapper<RabbitLog>()
                        .solveGenericParameters(parameter)
                        .le(RabbitLog::getTimestamp, parameter.getTimestamp())
                        .in(!ListUtil.isEmpty(parameter.getRids()), RabbitLog::getRid, parameter.getRids())
        );
    }

    @Override
    public void save(RabbitLogSaveParameter parameter) {
        RabbitLog rabbitLog = new RabbitLog();
        BeanUtils.copyProperties(parameter, rabbitLog);
        saveTE(rabbitLog);
        parameter.setRid(rabbitLog.getRid());
    }

    @Override
    public void update(RabbitLogUpdateParameter parameter) {
        RabbitLog rabbitLog = new RabbitLog();
        BeanUtils.copyProperties(parameter, rabbitLog);
        updateByIdTE(rabbitLog);
    }
}
