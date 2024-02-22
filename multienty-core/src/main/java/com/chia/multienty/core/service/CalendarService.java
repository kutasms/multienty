package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.CalendarDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Calendar;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.CalendarDetailGetParameter;
import com.chia.multienty.core.parameter.base.CalendarPageGetParameter;
import com.chia.multienty.core.parameter.base.CalendarDeleteParameter;
import com.chia.multienty.core.parameter.base.CalendarSaveParameter;
import com.chia.multienty.core.parameter.base.CalendarUpdateParameter;

/**
 * <p>
 * 用于链表的连续日期信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface CalendarService extends KutaBaseService<Calendar> {

    CalendarDTO getDetail(CalendarDetailGetParameter parameter);

    void delete(CalendarDeleteParameter parameter);


    IPage<CalendarDTO> getPage(CalendarPageGetParameter parameter);

    void save(CalendarSaveParameter parameter);

    void update(CalendarUpdateParameter parameter);


}
