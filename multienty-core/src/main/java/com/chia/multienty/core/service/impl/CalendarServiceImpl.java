package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.CalendarDTO;
import com.chia.multienty.core.mapper.CalendarMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Calendar;
import com.chia.multienty.core.service.CalendarService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.CalendarDetailGetParameter;
import com.chia.multienty.core.parameter.base.CalendarPageGetParameter;
import com.chia.multienty.core.parameter.base.CalendarDeleteParameter;
import com.chia.multienty.core.parameter.base.CalendarSaveParameter;
import com.chia.multienty.core.parameter.base.CalendarUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 日历 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-16
 */
@Service
public class CalendarServiceImpl extends KutaBaseServiceImpl<CalendarMapper, Calendar> implements CalendarService {


    @Override
    public CalendarDTO getDetail(CalendarDetailGetParameter parameter) {
        return selectJoinOne(CalendarDTO.class,
                        MPJWrappers.<Calendar>lambdaJoin().eq(Calendar::getDay, parameter.getDay()));
    }

    @Override
    public void delete(CalendarDeleteParameter parameter) {
        removeByIdTE(parameter.getDay());
    }

    @Override
    public IPage<CalendarDTO> getPage(CalendarPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), CalendarDTO.class,
                new MTLambdaWrapper<Calendar>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getDays()), Calendar::getDay, parameter.getDays())
        );
    }

    @Override
    public void save(CalendarSaveParameter parameter) {
        Calendar calendar = new Calendar();
        BeanUtils.copyProperties(parameter, calendar);
        saveTE(calendar);
        parameter.setDay(calendar.getDay());
    }

    @Override
    public void update(CalendarUpdateParameter parameter) {
        Calendar calendar = new Calendar();
        BeanUtils.copyProperties(parameter, calendar);
        updateByIdTE(calendar);
    }
}
