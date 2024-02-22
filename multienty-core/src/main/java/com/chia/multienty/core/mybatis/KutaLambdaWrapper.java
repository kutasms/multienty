package com.chia.multienty.core.mybatis;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.basic.CityVO;
import com.chia.multienty.core.domain.enums.QueryMode;
import com.chia.multienty.core.pojo.SearchEntity;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.util.KutaCollectionUtil;
import com.chia.multienty.core.mybatis.constants.LikeMode;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;

public class KutaLambdaWrapper<T> extends MPJLambdaWrapper<T>{

    private static String createTimeColName;
    private static String cityIdColName;
    private static String statusColName;
    private static String mockDataColName;

    static {
        LambdaMeta extract = LambdaUtils.extract(SearchEntity::getCreateTime);
        createTimeColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        extract = LambdaUtils.extract(SearchEntity::getCityId);
        cityIdColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        extract = LambdaUtils.extract(SearchEntity::getStatus);
        statusColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        extract = LambdaUtils.extract(SearchEntity::getMockData);
        mockDataColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
    }

    /**
     * 应用默认列表请求参数
     * <p>
     *     包括创建时间范围、是否已删除、城市筛选、状态
     * </p>
     * @param parameter
     * @return
     */
    public KutaLambdaWrapper<T> solveGenericParameters(DefaultListGetParameter<?> parameter) {
        Class<?> clazz = (Class<?>) ((ParameterizedType)parameter.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = KutaBeanUtil.getAllDeclaredFields(clazz);

        boolean hasCreateTimeCol = KutaBeanUtil.hasField(fields, createTimeColName);
        boolean hasCityIdCol = KutaBeanUtil.hasField(fields, cityIdColName);
        boolean hasStatusCol = KutaBeanUtil.hasField(fields, statusColName);
        boolean hasMockDataCol = KutaBeanUtil.hasField(fields, mockDataColName);


        return  (KutaLambdaWrapper<T>)this.createTimeDuration(hasCreateTimeCol, parameter.getCreateTimeDuration(), SearchEntity::getCreateTime)
                .likeCity(parameter.getCity() != null && hasCityIdCol, parameter.getCity(), SearchEntity::getCityId)
                .eq(parameter.getStatus()!=null && hasStatusCol, SearchEntity::getStatus, parameter.getStatus())
                .eq(parameter.getMockData()!=null && hasMockDataCol, SearchEntity::getMockData, parameter.getMockData())
                .orderByDesc(parameter.getOrderByDescColumns()!=null, parameter.getOrderByDescColumns())
                .orderByAsc(parameter.getOrderByAscColumns()!=null, parameter.getOrderByAscColumns())
                .in(!KutaCollectionUtil.isEmpty(parameter.getStatusList()), SearchEntity::getStatus, parameter.getStatusList());
    }

    public <X> KutaLambdaWrapper<T> tryLeftJoin(boolean condition, Class<X> clazz, SFunction<X, ?> left, SFunction<T, ?> right) {
        if(condition) {
            this.leftJoin(clazz, left, right);
        }
        return this;
    }

    public <S, C, F> KutaLambdaWrapper<T> trySelectAssociation(boolean condition, Class<C> child, SFunction<S, F> dtoField) {
        if(condition) {
            selectAssociation(child, dtoField);
        }
        return this;
    }

    public <X> KutaLambdaWrapper<T> createTimeDuration(boolean hasCreateTimeCol,LocalDateTime[] duration, SFunction<X,?> column) {
        this.and(duration != null && hasCreateTimeCol && duration.length == 2, on ->
                on.ge(duration[0] != null, column, duration[0])
                        .le(duration[1] != null, column, duration[1])
        );
        return this;
    }

    public <X> KutaLambdaWrapper<T> createTimeDuration(LocalDateTime[] duration, SFunction<X,?> column) {
        this.and(duration != null, on ->
                on.ge(duration[0] != null, column, duration[0])
                        .le(duration[1] != null, column, duration[1])
                );
        return this;
    }

    public <X> KutaLambdaWrapper<T> likeCity(CityVO city, SFunction<X,?> column) {
        if(city != null) {
            this.eq(city !=null && city.getIds().length == 3, column, city.getCityId())
                    .likeRight(city !=null && city.getIds().length == 2,
                            column, city.getCityId().toString().substring(0,4))
                    .likeRight(city !=null && city.getIds().length == 1,
                            column, city.getCityId().toString().substring(0,2));

        }
        return this;
    }
    public <X> KutaLambdaWrapper<T> likeCity(boolean condition, CityVO city, SFunction<X,?> column) {
        return
                condition ?
                (KutaLambdaWrapper<T>)this.eq(city !=null && city.getIds().length == 3, column, city.getCityId())
                .likeRight(city !=null && city.getIds().length == 2,
                        column, city.getCityId().toString().substring(0,4))
                .likeRight(column!=null && city.getIds().length == 1,
                        column, city.getCityId().toString().substring(0,2))
                : this;
    }

    public <X> KutaLambdaWrapper<T> likeList(List<String> likes,SFunction<X,?> column, LikeMode mode) {
        if(likes!=null && likes.size() > 0) {
            this.and(ew-> {
                likes.forEach(like-> {
                    if(!StringUtils.isBlank(like)) {
                        switch (mode) {
                            case LEFT:
                                ew.or().likeLeft(column, like);
                                break;
                            case RIGHT:
                                ew.or().likeRight(column, like);
                                break;
                            case ALL:
                                ew.or().like(column, like);
                                break;
                        }
                    }
                });
            });
        }

        return this;
    }
    public <X> KutaLambdaWrapper<T> applyFieldMode(Object parameter, SFunction<X,?> column, QueryMode mode) {
        if(parameter!=null) {
            MPJLambdaWrapper<T> wrapper = this;
            switch (mode) {
                case GT:
                    wrapper = this.gt(column, parameter);
                    break;
                case GTE:
                    wrapper = this.ge(column, parameter);
                    break;
                case LT:
                    wrapper = this.lt(column, parameter);
                    break;
                case LTE:
                    wrapper = this.le(column, parameter);
                    break;
                case EQ:
                    wrapper = this.eq(column, parameter);
                    break;
            }
            return (KutaLambdaWrapper)wrapper;
        }
        return this;
    }

}
