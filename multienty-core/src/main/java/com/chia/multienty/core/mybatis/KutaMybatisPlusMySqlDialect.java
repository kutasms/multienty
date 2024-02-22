package com.chia.multienty.core.mybatis;


import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;

import java.util.regex.Pattern;

public class KutaMybatisPlusMySqlDialect extends MySqlDialect {
    @Override
    public DialectModel buildPaginationSql(String originalSql, long offset, long limit) {
        Pattern compileFixed = Pattern.compile("/\\*fixed\\*/");
        String compileLimit = "/\\*limit\\*/";
        if(compileFixed.matcher(originalSql).find()) {
            if(offset != 0L) {
                originalSql = originalSql.replaceFirst(compileLimit, " LIMIT ?,? ");
                return (new DialectModel(originalSql, offset, limit)).setConsumerChain();
            } else{
                originalSql = originalSql.replaceFirst(compileLimit, " LIMIT ? ");
                return (new DialectModel(originalSql, limit)).setConsumer(true);
            }
        }

        return super.buildPaginationSql(originalSql, offset, limit);
    }
}
