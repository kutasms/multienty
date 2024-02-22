package com.chia.multienty.core.tools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@Slf4j
public class TableCopier {
    private final JdbcTemplate jdbcTemplate;

    public void setAutoIncrement(String tableName) {
        String sql = "SELECT @@auto_increment.nextval AS next_id FROM information_schema.tables WHERE table_name = '%s';SET auto_increment=next_id+1 ON %s;";
        sql = String.format(sql, tableName, tableName);
        log.info("设置自增长列，执行SQL：{}", sql);
        jdbcTemplate.execute(sql);
    }

    public void setAutoIncrement(String tableName, Integer autoIncrement) {
        String sql = "ALTER TABLE %s AUTO_INCREMENT=%s;";
        sql = String.format(sql, tableName, autoIncrement);
        log.info("设置自增长列，执行SQL：{}", sql);
        jdbcTemplate.execute(sql);
    }

    public void copyTable(String sourceTableName, String targetTableName) {
        // 创建目标表结构
        createTargetTableStructure(sourceTableName, targetTableName);

        // 从源表查询数据并插入到目标表
        insertDataFromSourceToTarget(sourceTableName, targetTableName);
    }

    public void copyTableConstruct(String sourceTableName, String targetTableName) {
        // 创建目标表结构
        createTargetTableStructure(sourceTableName, targetTableName);
    }


    public void copyTableConstruct(String sourceTableName, String targetTableName, Statement statement) throws SQLException {
        // 创建目标表结构
        createTargetTableStructure(sourceTableName, targetTableName, statement);
    }

    public void copyTableConstruct(String sourceTableName, String targetTableName, Long autoIncrement) {
        // 创建目标表结构
        createTargetTableStructure(sourceTableName, targetTableName);
    }

    private void createTargetTableStructure(String sourceTableName, String tableName, Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " LIKE " + sourceTableName + ";";
        log.info("复制表结构执行SQL：{}", sql);
        statement.execute(sql);
    }

    private void createTargetTableStructure(String sourceTableName, String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " LIKE " + sourceTableName + ";";
        log.info("复制表结构执行SQL：{}", sql);
        jdbcTemplate.execute(sql);
    }


    private void insertDataFromSourceToTarget(String sourceTableName, String targetTableName) {
        String sql = "INSERT INTO " + targetTableName + " SELECT * FROM " + sourceTableName;
        log.info("复制表数据执行SQL：{}", sql);
        jdbcTemplate.update(sql);
    }
}
