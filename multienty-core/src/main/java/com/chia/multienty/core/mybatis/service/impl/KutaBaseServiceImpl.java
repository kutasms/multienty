package com.chia.multienty.core.mybatis.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.mybatis.KutaBaseMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.pojo.SearchEntity;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.exception.OptimisticLockUpdateFailureException;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class KutaBaseServiceImpl<M extends KutaBaseMapper<T>, T> extends MPJBaseServiceImpl<M, T> implements KutaBaseService<T> {

    private final String OPTIMISTIC_LOCK_FIELD = "version";

    @Override
    public Class<T> currentModelClass() {
        return super.currentModelClass();
    }

    @Override
    public <D> T getBy(Serializable id,
                   SFunction<D, ?>... columns) {
        return baseMapper.getByPrimaryKey(id, Arrays.asList(columns));
    }

    /**
     * 获取DTO对象
     * */
    public <D> D getDTO(Class<D> clazz, Serializable id, SFunction<T, ?> column) {
        return baseMapper.selectJoinOne(clazz,
                new MPJLambdaWrapper<T>()
                        .eq(column, id));
    }

    protected MTLambdaWrapper<T> mtLambdaWrapper() {
        return new MTLambdaWrapper<>();
    }

    @Override
    public Long getPrimaryKeyVal(Object entity) throws IllegalAccessException {
        return getPrimaryKeyVal(entity, null);
    }

    @Override
    public Field getPrimaryKey(Object entity) {
        Field[] fields = KutaBeanUtil.getAllDeclaredFields(entity.getClass());
        return getPrimaryKey(entity, fields);
    }

    @Override
    public Field getPrimaryKey(Object entity, Field[] fields) {
        if(fields == null) {
            fields = KutaBeanUtil.getAllDeclaredFields(entity.getClass());
        }
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.getAnnotation(TableId.class) != null) {
                return field;
            }
        }
        return null;
    }

    @Override
    public Long getPrimaryKeyVal(Object entity, Field[] fields) {
        if(fields == null) {
            fields = KutaBeanUtil.getAllDeclaredFields(entity.getClass());
        }
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.getAnnotation(TableId.class) != null) {
                try {
                    return (long)field.get(entity);
                }
                catch (IllegalAccessException | IllegalArgumentException ex) {
                    throw new RuntimeException(ex);
                }
                finally {
                    field.setAccessible(false);
                }
            }
        }
        return null;
    }

    @Override
    public void saveTE(T entity) {
        if(!save(entity)) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DATABASE_ERROR);
        }
    }

    @Override
    public <DTO extends T> void saveDTOTE(DTO dto) {
        if(!save(dto)) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DATABASE_ERROR);
        }
    }

    @Override
    public void removeTE(Wrapper<T> queryWrapper) {
        if(!remove(queryWrapper)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_DELETE_FAILURE);
        }
    }

    @Override
    public void removeByIdTE(Serializable id) {
        if(!removeById(id)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_DELETE_FAILURE);
        }
    }

    @Override
    public void saveBatchTE(List<T> list) {
        if(!saveBatch(list)) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DATABASE_ERROR);
        }
    }

    @Override
    public void saveOrUpdateBatchTE(List<T> list) {
        if(!saveOrUpdateBatch(list)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_UPDATE_FAILURE);
        }
    }

    @Override
    public <DTO extends T> List<DTO> dtoListByColumnIds(Class<DTO> clazz, SFunction<T, ?> column, Collection<?> ids) {
        return selectJoinList(clazz, new KutaLambdaWrapper<T>().in(column, ids));
    }

    @Override
    public <DTO extends T> List<DTO> dtoListBy(Class<DTO> clazz, Consumer<KutaLambdaWrapper<T>> consumer) {
        KutaLambdaWrapper<T> wrapper = new KutaLambdaWrapper<T>();
        consumer.accept(wrapper);
        return selectJoinList(clazz, wrapper);
    }

    @Override
    public <DTO extends T> DTO dtoBy(Class<DTO> clazz, Consumer<KutaLambdaWrapper<T>> consumer) {
        KutaLambdaWrapper<T> wrapper = wrapper();
        consumer.accept(wrapper);
        return selectJoinOne(clazz, wrapper);
    }

    @Override
    public <DTO extends T> void batchSaveDTOTE(List<DTO> list) {
        if(!saveBatch(list.stream().map(m-> (T)m).collect(Collectors.toList()))) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DATABASE_ERROR);
        }
    }

    @Override
    public boolean updateByIdTE(T entity) {
        if(!updateById(entity)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_UPDATE_FAILURE);
        }
        return true;
    }

    @Override
    public KutaLambdaWrapper<T> wrapper() {
        return new KutaLambdaWrapper<T>();
    }

    @Override
    public boolean batchUpdateByIdTE(Collection<T> entityList, int batchSize) {
        if(!updateBatchById(entityList, batchSize)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_UPDATE_FAILURE);
        }
        return true;
    }

    @Override
    public boolean batchUpdateByIdTE(Collection<T> entityList) {
        if(!updateBatchById(entityList)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_UPDATE_FAILURE);
        }
        return true;
    }

    @Override
    public <DTO extends T> boolean batchUpdateDTOByIdTE(Collection<DTO> entityList) {
        List<T> list = entityList.stream().map(m -> (T) m).collect(Collectors.toList());
        if(!updateBatchById(list)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_UPDATE_FAILURE);
        }
        return true;
    }

    @Override
    public <DTO extends T> boolean batchRemoveByIdTE(Collection<? extends Serializable> ids) {
        if(!removeByIds(ids)) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_DELETE_FAILURE);
        }
        return true;
    }

    @Override
    public boolean updateByIdWithVersion(T entity) {
        Field[] fields = KutaBeanUtil.getAllDeclaredFields(entity.getClass());
        if(KutaBeanUtil.hasField(fields, OPTIMISTIC_LOCK_FIELD)) {
            if(!updateById(entity)) {
                Long primaryKeyVal = getPrimaryKeyVal(entity, fields);

                long oldVersion = (long) KutaBeanUtil.getFieldVal(entity,fields, OPTIMISTIC_LOCK_FIELD);
                if(primaryKeyVal != null) {
                    if(TransactionSynchronizationManager.isActualTransactionActive()) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }

                    T record = getBy(primaryKeyVal, SearchEntity::getVersion);
                    Object newVersion = KutaBeanUtil.getFieldVal(record, fields, OPTIMISTIC_LOCK_FIELD);
                    // 数据库中版本号已变更，导致数据更新失败
                    if((long)newVersion >= oldVersion) {
                        throw new OptimisticLockUpdateFailureException();
                    }
                    throw new KutaRuntimeException(HttpResultEnum.DATA_UPDATE_FAILURE);
                } else {
                    throw new KutaRuntimeException(HttpResultEnum.PRIMARY_KEY_NULL);
                }
            } else {
                return true;
            }
        } else {
            return updateByIdTE(entity);
        }
    }
}
