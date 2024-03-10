package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.WebLogDTO;
import com.chia.multienty.core.mapper.WebLogMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WebLog;
import com.chia.multienty.core.service.WebLogService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.log.WebLogDetailGetParameter;
import com.chia.multienty.core.parameter.log.WebLogPageGetParameter;
import com.chia.multienty.core.parameter.log.WebLogDeleteParameter;
import com.chia.multienty.core.parameter.log.WebLogSaveParameter;
import com.chia.multienty.core.parameter.log.WebLogUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * web请求记录 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@Service
public class WebLogServiceImpl extends KutaBaseServiceImpl<WebLogMapper, WebLog> implements WebLogService {


    @Override
    public WebLogDTO getDetail(WebLogDetailGetParameter parameter) {
        return selectJoinOne(WebLogDTO.class,
                        MPJWrappers.<WebLog>lambdaJoin().eq(WebLog::getLogId, parameter.getLogId()));
    }

    @Override
    public void delete(WebLogDeleteParameter parameter) {
        removeByIdTE(parameter.getLogId());
    }

    @Override
    public IPage<WebLogDTO> getPage(WebLogPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WebLogDTO.class,
                new MTLambdaWrapper<WebLog>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getLogIds()), WebLog::getLogId, parameter.getLogIds())
                        .eq(parameter.getMetaId() != null, WebLog::getMetaId, parameter.getMetaId())
                        .eq(parameter.getType() != null, WebLog::getType, parameter.getType())
                        .orderByDesc(WebLog::getLogId)
        );
    }

    @Override
    public void save(WebLogSaveParameter parameter) {
        WebLog webLog = new WebLog();
        BeanUtils.copyProperties(parameter, webLog);
        saveTE(webLog);
        parameter.setLogId(webLog.getLogId());
    }

    @Override
    public void update(WebLogUpdateParameter parameter) {
        WebLog webLog = new WebLog();
        BeanUtils.copyProperties(parameter, webLog);
        updateByIdTE(webLog);
    }
}
