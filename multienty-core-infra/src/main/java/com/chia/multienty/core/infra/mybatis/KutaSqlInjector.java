package com.chia.multienty.core.infra.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.chia.multienty.core.infra.mybatis.method.DeleteByIdAndShardingMethod;
import com.chia.multienty.core.infra.mybatis.method.GetByIdAndShardingMethod;
import com.chia.multienty.core.infra.mybatis.method.UpdateByIdAndShardingMethod;
import com.chia.multienty.core.mybatis.method.CountByMethod;
import com.chia.multienty.core.mybatis.method.GetByPrimaryKeyMethod;
import com.github.yulichang.injector.MPJSqlInjector;
import lombok.Getter;

import java.util.List;

@Getter
public class KutaSqlInjector extends MPJSqlInjector  {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new CountByMethod());
        methodList.add(new GetByPrimaryKeyMethod());
        methodList.add(new UpdateByIdAndShardingMethod());
        methodList.add(new DeleteByIdAndShardingMethod());
        methodList.add(new GetByIdAndShardingMethod());
        return methodList;
    }

}
