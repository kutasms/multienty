package com.chia.multienty.core.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface KutaBaseMapper<T> extends MPJBaseMapper<T> {
    Long countBy(@Param(Constants.WRAPPER) Wrapper wrapper);

    T getByPrimaryKey(@Param("id") Serializable id,
                      @Param(KutaMybatisConstants.SFUNC)
                      List<SFunction<?, ?>> columns);

}
