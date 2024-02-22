package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.ChineseCityDTO;
import com.chia.multienty.core.domain.vo.LabelValuePair;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.pojo.ChineseCity;

import java.util.List;

/**
 * <p>
 * 城市管理信息表 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface ChineseCityService extends KutaBaseService<ChineseCity> {

    ChineseCityDTO getDetail(ChineseCityDetailGetParameter parameter);

    void delete(ChineseCityDeleteParameter parameter);

    IPage<ChineseCityDTO> getPage(ChineseCityPageGetParameter parameter);

    void save(ChineseCitySaveParameter parameter);

    void update(ChineseCityUpdateParameter parameter);


    List<LabelValuePair> getList(CityListGetParameter parameter);

    ChineseCityDTO getFull(CityGetParameter parameter) throws KutaRuntimeException;
}
