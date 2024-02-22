package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.ChineseCityDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.LabelValuePair;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mapper.ChineseCityMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.pojo.ChineseCity;
import com.chia.multienty.core.service.ChineseCityService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 城市管理信息表 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class ChineseCityServiceImpl extends KutaBaseServiceImpl<ChineseCityMapper, ChineseCity> implements ChineseCityService {


    @Override
    public ChineseCityDTO getDetail(ChineseCityDetailGetParameter parameter) {
        return selectJoinOne(ChineseCityDTO.class,
                        MPJWrappers.<ChineseCity>lambdaJoin().eq(ChineseCity::getCityId, parameter.getCityId()));
    }

    @Override
    public void delete(ChineseCityDeleteParameter parameter) {
        removeByIdTE(parameter.getCityId());
    }

    @Override
    public IPage<ChineseCityDTO> getPage(ChineseCityPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), ChineseCityDTO.class,
                new KutaLambdaWrapper<ChineseCity>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getCityIds()), ChineseCity::getCityId, parameter.getCityIds())
        );
    }

    @Override
    public void save(ChineseCitySaveParameter parameter) {
        ChineseCity chineseCity = new ChineseCity();
        BeanUtils.copyProperties(parameter, chineseCity);
        saveTE(chineseCity);
        parameter.setCityId(chineseCity.getCityId());
    }

    @Override
    public void update(ChineseCityUpdateParameter parameter) {
        ChineseCity chineseCity = new ChineseCity();
        BeanUtils.copyProperties(parameter, chineseCity);
        updateByIdTE(chineseCity);
    }

    @Override
    public List<LabelValuePair> getList(CityListGetParameter parameter){
        List<ChineseCity> list = baseMapper.selectList(
                new LambdaQueryWrapper<ChineseCity>()
                        .eq(ChineseCity::getCityPid, parameter.getParentId())
                        .select(ChineseCity::getCityId, ChineseCity::getCityName));
        if(ListUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(m -> new LabelValuePair(m.getCityName(),m.getCityId(),m.getCityId() % 100 != 0)).collect(Collectors.toList());
    }

    @Override
    public ChineseCityDTO getFull(CityGetParameter parameter) throws KutaRuntimeException {
        ChineseCityDTO city = new ChineseCityDTO();
        if(parameter.getProvinceName() == null) {
            throw new KutaRuntimeException(HttpResultEnum.CITY_SELECT_PROVINCE_NOT_NULL);
        }

        city.setProvince(baseMapper.selectOne(
                new LambdaQueryWrapper<ChineseCity>()
                        .eq(ChineseCity::getCityName, parameter.getProvinceName())
                        .eq(ChineseCity::getCityPid, 0)
        ));
        if(city.getProvince() == null) {
            throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
        }
        if(parameter.getCityName() != null) {
            city.setCity(baseMapper.selectOne(
                    new LambdaQueryWrapper<ChineseCity>()
                            .eq(ChineseCity::getCityName, parameter.getCityName())
                            .eq(ChineseCity::getCityPid, city.getProvince().getCityId())
            ));
            if(city.getCity() == null) {
                throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
            }
        }
        if(parameter.getDistrictName()!=null) {
            city.setDistrict(baseMapper.selectOne(
                    new LambdaQueryWrapper<ChineseCity>()
                            .eq(ChineseCity::getCityName, parameter.getDistrictName())
                            .eq(ChineseCity::getCityPid, city.getCity().getCityId())
            ));
            if(city.getDistrict() == null) {
                throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
            }
        }
        return city;
    }
}
