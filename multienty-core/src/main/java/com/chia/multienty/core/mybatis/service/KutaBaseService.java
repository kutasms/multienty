package com.chia.multienty.core.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.github.yulichang.base.MPJBaseService;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface KutaBaseService<T> extends MPJBaseService<T> {
    <D> T getBy(Serializable id,
            SFunction<D, ?>... columns);

    Long getPrimaryKeyVal(Object entity) throws IllegalAccessException;

    Field getPrimaryKey(Object entity);

    Field getPrimaryKey(Object entity, Field[] fields);

    Long getPrimaryKeyVal(Object entity, Field[] fields);

    void saveTE(T entity);

    <DTO extends T> void saveDTOTE(DTO dto);

    void removeTE(Wrapper<T> queryWrapper);

    void removeByIdTE(Serializable id);

    void saveBatchTE(List<T> list);


    void saveOrUpdateBatchTE(List<T> list);

    <DTO extends T> List<DTO> dtoListByColumnIds(Class<DTO> clazz, SFunction<T, ?> column, Collection<?> ids);

    <DTO extends T> List<DTO> dtoListBy(Class<DTO> clazz, Consumer<KutaLambdaWrapper<T>> consumer);

    <DTO extends T> DTO dtoBy(Class<DTO> clazz, Consumer<KutaLambdaWrapper<T>> consumer);

    <DTO extends T> void batchSaveDTOTE(List<DTO> list);

    boolean updateByIdTE(T entity);

    KutaLambdaWrapper<T> wrapper();

    boolean batchUpdateByIdTE(Collection<T> entityList, int batchSize);

    boolean batchUpdateByIdTE(Collection<T> entityList);

    <DTO extends T> boolean batchUpdateDTOByIdTE(Collection<DTO> entityList);

    <DTO extends T> boolean batchRemoveByIdTE(Collection<? extends Serializable> ids);

    boolean updateByIdWithVersion(T entity);
}
