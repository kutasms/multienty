package com.chia.multienty.core.mapper;

import com.chia.multienty.core.pojo.ShardingTable;
import com.chia.multienty.core.mybatis.KutaBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShardingTableMapper extends KutaBaseMapper<ShardingTable> {

    @Select("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE ENGINE = 'InnoDB' " +
            "AND TABLE_NAME NOT IN ('flyway_schema_history','undo_log');")
    //AND TABLE_NAME NOT RLIKE '_\d{1,6}$'
    public List<String> getTableNames();

    @Update("CREATE TABLE ${newTable} ( LIKE ${originalTable});")
    void copyTable(@Param("originalTable") String originalTable, @Param("newTable") String newTable);
}
