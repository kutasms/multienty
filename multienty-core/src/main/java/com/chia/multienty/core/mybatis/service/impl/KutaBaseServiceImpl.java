package com.chia.multienty.core.mybatis.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.exception.OptimisticLockUpdateFailureException;
import com.chia.multienty.core.mybatis.KutaBaseMapper;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.SearchEntity;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.util.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.binding.MapperMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    public Class<T> currentModelClass() {
        return super.currentModelClass();
    }

    @Override
    public <D> T getBy(Serializable id,
                   SFunction<D, ?>... columns) {

        return baseMapper.getByPrimaryKey(id, Arrays.asList(columns));
    }

    @Override
    public T getByIdAndSharding(T entity) {
        return baseMapper.getByIdAndSharding(entity);
    }


    @Override
    public <DTO extends T> boolean updateByIdAndSharding(DTO entity) {
        if(null == entity) {
            return false;
        }
        return SqlHelper.retBool(baseMapper.updateByIdAndSharding(entity));
    }

    @Override
    public <DTO extends T> boolean updateBatchByIdAndSharding(Collection<DTO> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(KutaSqlMethod.UPDATE_BY_ID_AND_SHARDING);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <DTO extends T> boolean updateBatchByIdAndSharding(Collection<DTO> entityList) {
        return updateBatchByIdAndSharding(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean removeByIdAndSharding(T entity) {
        if(null == entity) {
            return false;
        }
        return SqlHelper.retBool(getBaseMapper().deleteByIdAndSharding(entity));
    }

    @Override
    public boolean removeBatchByIdAndSharding(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(KutaSqlMethod.DELETE_BY_ID_AND_SHARDING);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.delete(sqlStatement, param);
        });
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBatchByIdAndSharding(Collection<T> entityList){
        return removeBatchByIdAndSharding(entityList, DEFAULT_BATCH_SIZE);
    }

    protected String getSqlStatement(KutaSqlMethod sqlMethod) {
        return getSqlStatement(mapperClass, sqlMethod);
    }
    public static String getSqlStatement(Class<?> mapper, KutaSqlMethod sqlMethod) {
        return mapper.getName() + StringPool.DOT + sqlMethod.getName();
    }

    @Override
    public String getCacheKey(Long id) {
        return String.format("%s-%s", currentModelClass().getSimpleName().toUpperCase(), id);
    }

    @Override
    public void cacheObj(T obj, long expire) throws IllegalAccessException, JsonProcessingException {
        Long primaryKeyVal = getPrimaryKeyVal(obj);
        StringRedisService stringRedisService = SpringUtil.getBean(StringRedisService.class);
        stringRedisService.set(getCacheKey(primaryKeyVal), objectMapper.writeValueAsString(obj), expire);
    }

    @Override
    public T getCachedObj(Long id) throws JsonProcessingException {
        StringRedisService stringRedisService = SpringUtil.getBean(StringRedisService.class);
        return stringRedisService.get(getCacheKey(id), new TypeReference<T>() {
        });
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

    protected LambdaQueryWrapper<T> lambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
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
        return selectJoinList(clazz, new MTLambdaWrapper<T>().in(column, ids));
    }

    @Override
    public <DTO extends T> List<DTO> dtoListBy(Class<DTO> clazz, Consumer<MTLambdaWrapper<T>> consumer) {
        MTLambdaWrapper<T> wrapper = new MTLambdaWrapper<T>();
        consumer.accept(wrapper);
        return selectJoinList(clazz, wrapper);
    }

    @Override
    public <DTO extends T> DTO dtoBy(Class<DTO> clazz, Consumer<MTLambdaWrapper<T>> consumer) {
        MTLambdaWrapper<T> wrapper = wrapper();
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
    public MTLambdaWrapper<T> wrapper() {
        return new MTLambdaWrapper<T>();
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
