package com.chia.multienty.core.mybatis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 库塔MYBATIS扩展方法名枚举
 * */
@Getter
@ApiModel(value = "KutaSqlMethod", description = "库塔MYBATIS扩展方法名枚举")
@AllArgsConstructor
public enum KutaSqlMethod {
    COUNT_BY("获取行数量","countBy", "<script>%s SELECT COUNT(1) FROM %s %s %s\n</script>"),
    GET_BY_PRIMARY_KEY("通过主键返回行并指定列","getByPrimaryKey","<script><bind name=\"column_format\" value=\"@com.chia.kuta.kernel.mybatis.OGNL.OGNLSFunctionBinding@parseToString(sfunc)\" />SELECT ${column_format} FROM %s WHERE %s = #{id}</script>");
    @ApiModelProperty("说明")
    private String desc;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("sql")
    private String sql;
}
