package com.chia.multienty.core.service;

import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Num;
import com.chia.multienty.core.parameter.base.NumSaveParameter;
import com.chia.multienty.core.parameter.base.NumUpdateParameter;

/**
 * <p>
 * 数字信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface NumService extends KutaBaseService<Num> {


    void save(NumSaveParameter parameter);

    void update(NumUpdateParameter parameter);


}
