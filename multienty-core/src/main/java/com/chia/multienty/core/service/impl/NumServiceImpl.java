package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.mapper.NumMapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Num;
import com.chia.multienty.core.service.NumService;
import org.springframework.stereotype.Service;
import com.chia.multienty.core.parameter.base.NumSaveParameter;
import com.chia.multienty.core.parameter.base.NumUpdateParameter;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 数字信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class NumServiceImpl extends KutaBaseServiceImpl<NumMapper, Num> implements NumService {


    @Override
    public void save(NumSaveParameter parameter) {
        Num num = new Num();
        BeanUtils.copyProperties(parameter, num);
        saveTE(num);
    }

    @Override
    public void update(NumUpdateParameter parameter) {
        Num num = new Num();
        BeanUtils.copyProperties(parameter, num);
        updateByIdTE(num);
    }
}
