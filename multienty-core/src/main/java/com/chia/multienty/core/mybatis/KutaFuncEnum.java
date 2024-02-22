package com.chia.multienty.core.mybatis;

import com.github.yulichang.wrapper.enums.BaseFuncEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum KutaFuncEnum implements BaseFuncEnum {
    DATE_FORMAT("DATE_FORMAT(%s,'%%Y-%%m-%%d')"),         //日期格式化
    IF_SEX("IF(%s=1,'男','女')"),                         //if 性别转换
    CASE_SEX("CASE %s WHEN 1 THEN '男' ELSE '女' END"),   //case 性别转换
    CASE_ADMIN("CASE WHEN SUM(CASE %s WHEN 1 THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END"),
    FIRST("FIRST(%s)"),
    LAST("LAST(%s)"),
    UCASE("UCASE(%s)"),
    LCASE("LCASE(%s)"),
    IFNULL_THEN_ZERO("IFNULL(%s, 0)"),
    GROUP_CONCAT("GROUP_CONCAT(%s)"),
    MIN("min(%s)"),
    MAX("max(%s)");

    private String pattern;

    @Override
    public String getSql() {
        return this.getPattern();
    }
}
