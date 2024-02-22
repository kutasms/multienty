package com.chia.multienty.core.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.chia.multienty.core.mybatis.method.CountByMethod;
import com.chia.multienty.core.mybatis.method.GetByPrimaryKeyMethod;
import com.github.yulichang.injector.MPJSqlInjector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KutaSqlInjector extends MPJSqlInjector {
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new CountByMethod());
        methodList.add(new GetByPrimaryKeyMethod());

        return methodList;
    }
}
