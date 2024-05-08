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
    GET_BY_PRIMARY_KEY("通过主键返回行并指定列","getByPrimaryKey","<script><bind name=\"column_format\" value=\"@com.chia.multienty.core.mybatis.OGNL.OGNLSFunctionBinding@parseToString(sfunc)\" />SELECT ${column_format} FROM %s WHERE %s = #{id}</script>"),
    GET_BY_ID_AND_SHARDING("通过主键和分片键获取单条数据","getByIdAndSharding","<script>SELECT %s FROM %s WHERE %s=#{et.%s} %s %s</script>"),
    UPDATE_BY_ID_AND_SHARDING("通过主键和分片键更新", "updateByIdAndSharding", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s %s\n</script>"),
    DELETE_BY_ID_AND_SHARDING("根据ID和分片键批量删除数据", "deleteByIdAndSharding", "<script>\nDELETE FROM %s WHERE %s=#{et.%s}%s\n</script>"),
    LOGIC_DELETE_BY_ID_AND_SHARDING("根据ID和分片键批量逻辑删除数据", "deleteByIdAndSharding", "<script>\nUPDATE %s %s WHERE %s=#{et.%s} %s %s\n</script>"),
    GET_LIST_BY_SHARDING("结合分片键查询满足条件所有数据", "getListBySharding", "<script>%s SELECT %s FROM %s %s %s %s\n</script>"),;

    @ApiModelProperty("说明")
    private String desc;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("sql")
    private String sql;
}
