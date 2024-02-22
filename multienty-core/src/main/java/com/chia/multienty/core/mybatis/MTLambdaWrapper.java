package com.chia.multienty.core.mybatis;

import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.basic.CityVO;
import com.chia.multienty.core.domain.enums.QueryMode;
import com.chia.multienty.core.pojo.SearchEntity;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.util.KutaCollectionUtil;
import com.chia.multienty.core.mybatis.constants.LikeMode;
import com.github.yulichang.config.ConfigProperties;
import com.github.yulichang.toolkit.*;
import com.github.yulichang.toolkit.LambdaUtils;
import com.github.yulichang.toolkit.support.ColumnCache;
import com.github.yulichang.wrapper.JoinAbstractLambdaWrapper;
import com.github.yulichang.wrapper.enums.IfAbsentSqlKeyWordEnum;
import com.github.yulichang.wrapper.interfaces.Chain;
import com.github.yulichang.wrapper.interfaces.Query;
import com.github.yulichang.wrapper.interfaces.QueryLabel;
import com.github.yulichang.wrapper.interfaces.SelectWrapper;
import com.github.yulichang.wrapper.resultmap.Label;
import com.github.yulichang.wrapper.segments.*;
import lombok.Getter;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "DuplicatedCode"})
public class MTLambdaWrapper<T> extends JoinAbstractLambdaWrapper<T, MTLambdaWrapper<T>> implements
        Query<MTLambdaWrapper<T>>, QueryLabel<MTLambdaWrapper<T>>, Chain<T>, SelectWrapper<T, MTLambdaWrapper<T>> {

    /**
     * 查询字段 sql
     */
    private SharedString sqlSelect = new SharedString();

    /**
     * 是否 select distinct
     */
    private boolean selectDistinct = false;
    /**
     * 查询的字段
     */
    @Getter
    private final List<Select> selectColumns = new ArrayList<>();
    /**
     * 映射关系
     */
    @Getter
    private final List<Label<?>> resultMapMybatisLabel = new ArrayList<>();

    /**
     * union sql
     */
    private SharedString unionSql;

    /**
     * 自定义wrapper索引
     */
    private AtomicInteger wrapperIndex;


    private static String createTimeColName;
    private static String cityIdColName;
    private static String statusColName;
    private static String mockDataColName;

    static {
        LambdaMeta extract = com.baomidou.mybatisplus.core.toolkit.LambdaUtils.extract(SearchEntity::getCreateTime);
        createTimeColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        extract = com.baomidou.mybatisplus.core.toolkit.LambdaUtils.extract(SearchEntity::getCityId);
        cityIdColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        extract = com.baomidou.mybatisplus.core.toolkit.LambdaUtils.extract(SearchEntity::getStatus);
        statusColName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        extract = com.baomidou.mybatisplus.core.toolkit.LambdaUtils.extract(SearchEntity::getMockData);
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
    public MTLambdaWrapper<T> solveGenericParameters(DefaultListGetParameter<?> parameter) {
        Class<?> clazz = (Class<?>) ((ParameterizedType)parameter.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = KutaBeanUtil.getAllDeclaredFields(clazz);

        boolean hasCreateTimeCol = KutaBeanUtil.hasField(fields, createTimeColName);
        boolean hasCityIdCol = KutaBeanUtil.hasField(fields, cityIdColName);
        boolean hasStatusCol = KutaBeanUtil.hasField(fields, statusColName);
        boolean hasMockDataCol = KutaBeanUtil.hasField(fields, mockDataColName);


        return  this.createTimeDuration(hasCreateTimeCol, parameter.getCreateTimeDuration(), SearchEntity::getCreateTime)
                .likeCity(parameter.getCity() != null && hasCityIdCol, parameter.getCity(), SearchEntity::getCityId)
                .eq(parameter.getStatus()!=null && hasStatusCol, SearchEntity::getStatus, parameter.getStatus())
                .eq(parameter.getMockData()!=null && hasMockDataCol, SearchEntity::getMockData, parameter.getMockData())
                .orderByDesc(parameter.getOrderByDescColumns()!=null, parameter.getOrderByDescColumns())
                .orderByAsc(parameter.getOrderByAscColumns()!=null, parameter.getOrderByAscColumns())
                .in(!KutaCollectionUtil.isEmpty(parameter.getStatusList()), SearchEntity::getStatus, parameter.getStatusList());
    }

    public <X> MTLambdaWrapper<T> tryLeftJoin(boolean condition, Class<X> clazz, SFunction<X, ?> left, SFunction<T, ?> right) {
        if(condition) {
            this.leftJoin(clazz, left, right);
        }
        return this;
    }

    public <S, C, F> MTLambdaWrapper<T> trySelectAssociation(boolean condition, Class<C> child, SFunction<S, F> dtoField) {
        if(condition) {
            selectAssociation(child, dtoField);
        }
        return this;
    }

    public <X> MTLambdaWrapper<T> createTimeDuration(boolean hasCreateTimeCol, LocalDateTime[] duration, SFunction<X,?> column) {
        this.and(duration != null && hasCreateTimeCol && duration.length == 2, on ->
                on.ge(duration[0] != null, column, duration[0])
                        .le(duration[1] != null, column, duration[1])
        );
        return this;
    }

    public <X> MTLambdaWrapper<T> createTimeDuration(LocalDateTime[] duration, SFunction<X,?> column) {
        this.and(duration != null, on ->
                on.ge(duration[0] != null, column, duration[0])
                        .le(duration[1] != null, column, duration[1])
        );
        return this;
    }

    public <X> MTLambdaWrapper<T> likeCity(CityVO city, SFunction<X,?> column) {
        if(city != null) {
            this.eq(city !=null && city.getIds().length == 3, column, city.getCityId())
                    .likeRight(city !=null && city.getIds().length == 2,
                            column, city.getCityId().toString().substring(0,4))
                    .likeRight(city !=null && city.getIds().length == 1,
                            column, city.getCityId().toString().substring(0,2));

        }
        return this;
    }
    public <X> MTLambdaWrapper<T> likeCity(boolean condition, CityVO city, SFunction<X,?> column) {
        return
                condition ?
                        this.eq(city !=null && city.getIds().length == 3, column, city.getCityId())
                                .likeRight(city !=null && city.getIds().length == 2,
                                        column, city.getCityId().toString().substring(0,4))
                                .likeRight(column!=null && city.getIds().length == 1,
                                        column, city.getCityId().toString().substring(0,2))
                        : this;
    }

    public <X> MTLambdaWrapper<T> likeList(List<String> likes,SFunction<X,?> column, LikeMode mode) {
        if(likes!=null && likes.size() > 0) {
            this.and(ew-> {
                likes.forEach(like-> {
                    if(!org.apache.commons.lang.StringUtils.isBlank(like)) {
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
    public <X> MTLambdaWrapper<T> applyFieldMode(Object parameter, SFunction<X,?> column, QueryMode mode) {
        if(parameter!=null) {
            MTLambdaWrapper<T> wrapper = this;
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
            return wrapper;
        }
        return this;
    }


    /**
     * 自定义wrapper
     */
    @Getter
    private Map<String, Wrapper<?>> wrapperMap;

    @Override
    protected MTLambdaWrapper<T> instance() {
        return instance(index, null, null, null);
    }

    @Override
    protected MTLambdaWrapper<T> instanceEmpty() {
        return new MTLambdaWrapper<>();
    }


    /**
     * 推荐使用 带 class 的构造方法
     */
    public MTLambdaWrapper() {
        super();
    }

    /**
     * 推荐使用此构造方法
     */
    public MTLambdaWrapper(Class<T> clazz) {
        super(clazz);
    }

    /**
     * 构造方法
     *
     * @param entity 主表实体
     */
    public MTLambdaWrapper(T entity) {
        super(entity);
    }

    /**
     * 自定义主表别名
     */
    public MTLambdaWrapper(String alias) {
        super(alias);
    }

    /**
     * 构造方法
     *
     * @param clazz 主表class类
     * @param alias 主表别名
     */
    public MTLambdaWrapper(Class<T> clazz, String alias) {
        super(clazz, alias);
    }

    /**
     * 构造方法
     *
     * @param entity 主表实体类
     * @param alias  主表别名
     */
    public MTLambdaWrapper(T entity, String alias) {
        super(entity, alias);
    }

    MTLambdaWrapper(T entity, Class<T> entityClass, SharedString sqlSelect, AtomicInteger paramNameSeq,
                     Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias,
                     SharedString lastSql, SharedString sqlComment, SharedString sqlFirst,
                     TableList tableList, Integer index, String keyWord, Class<?> joinClass, String tableName,
                     BiPredicate<Object, IfAbsentSqlKeyWordEnum> ifAbsent) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.sqlSelect = sqlSelect;
        this.paramAlias = paramAlias;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
        this.tableList = tableList;
        this.index = index;
        this.keyWord = keyWord;
        this.joinClass = joinClass;
        this.tableName = tableName;
        this.ifAbsent = ifAbsent;
    }

    @Override
    protected MTLambdaWrapper<T> instance(Integer index, String keyWord, Class<?> joinClass, String tableName) {
        return new MTLambdaWrapper<>(getEntity(), getEntityClass(), null, paramNameSeq, paramNameValuePairs,
                new MergeSegments(), this.paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString(),
                this.tableList, index, keyWord, joinClass, tableName, ifAbsent);
    }

    /**
     * sql去重
     * select distinct
     */
    public MTLambdaWrapper<T> distinct() {
        this.selectDistinct = true;
        return typedThis;
    }

    @Override
    public List<Select> getSelectColum()  {
        return this.selectColumns;
    }

    @Override
    public void addLabel(Label<?> label) {
        this.resultMap = true;
        this.resultMapMybatisLabel.add(label);
    }

    @Override
    public MTLambdaWrapper<T> getChildren() {
        return typedThis;
    }

    @Override
    public <E> MTLambdaWrapper<T> select(SFunction<E, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            Class<?> aClass = LambdaUtils.getEntityClass(columns[0]);
            Map<String, SelectCache> cacheMap = ColumnCache.getMapField(aClass);
            for (SFunction<E, ?> s : columns) {
                SelectCache cache = cacheMap.get(LambdaUtils.getName(s));
                getSelectColum().add(new SelectNormal(cache, index, hasAlias, alias));
            }
        }
        return typedThis;
    }

    @Override
    public MTLambdaWrapper<T> selectAll(Class<?> clazz) {
        return Query.super.selectAll(clazz);
    }

    /**
     * 子查询
     */
    public <E, F> MTLambdaWrapper<T> selectSub(Class<E> clazz, Consumer<MTLambdaWrapper<E>> consumer, SFunction<F, ?> alias) {
        return selectSub(clazz, ConfigProperties.subQueryAlias, consumer, alias);
    }

    /**
     * 子查询
     */
    public <E, F> MTLambdaWrapper<T> selectSub(Class<E> clazz, String st, Consumer<MTLambdaWrapper<E>> consumer, SFunction<F, ?> alias) {
        MTLambdaWrapper<E> wrapper = new MTLambdaWrapper<E>(null, clazz, SharedString.emptyString(),
                paramNameSeq, paramNameValuePairs, new MergeSegments(), new SharedString(this.paramAlias
                .getStringValue()), SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString(),
                new TableList(), null, null, null, null, ifAbsent) {
        };
        wrapper.tableList.setAlias(st);
        wrapper.tableList.setRootClass(clazz);
        wrapper.tableList.setParent(this.tableList);
        wrapper.alias = st;
        wrapper.subTableAlias = st;
        consumer.accept(wrapper);
        addCustomWrapper(wrapper);
        this.selectColumns.add(new SelectSub(() -> MTWrapperUtils.buildSubSqlByWrapper(clazz, wrapper, LambdaUtils.getName(alias)), hasAlias, this.alias));
        return typedThis;
    }

    /**
     * union
     * <p>
     * 推荐使用 union(Class<U> clazz, Consumer<MPJLambdaWrapper<U>> consumer)
     * 例： wrapper.union(UserDO.class, union -> union.selectAll(UserDO.class))
     *
     * @see #union(Class, Consumer)
     * @deprecated union 不支持子查询
     */
    @Deprecated
    @SuppressWarnings({"UnusedReturnValue", "DeprecatedIsStillUsed"})
    public final MTLambdaWrapper<T> union(MTLambdaWrapper<?>... wrappers) {
        StringBuilder sb = new StringBuilder();
        for (MTLambdaWrapper<?> wrapper : wrappers) {
            addCustomWrapper(wrapper);
            Class<?> entityClass = wrapper.getEntityClass();
            Assert.notNull(entityClass, "请使用 new MPJLambdaWrapper(主表.class) 或 JoinWrappers.lambda(主表.class) 构造方法");
            sb.append(" UNION ").append(MTWrapperUtils.buildUnionSqlByWrapper(entityClass, wrapper));
        }
        if (Objects.isNull(unionSql)) {
            unionSql = SharedString.emptyString();
        }
        unionSql.setStringValue(unionSql.getStringValue() + sb);
        return typedThis;
    }

    /**
     * union
     * <p>
     * 例： wrapper.union(UserDO.class, union -> union.selectAll(UserDO.class))
     *
     * @param clazz union语句的主表类型
     * @since 1.4.8
     */
    public <U> MTLambdaWrapper<T> union(Class<U> clazz, Consumer<MTLambdaWrapper<U>> consumer) {
        MTLambdaWrapper<U> unionWrapper = MTJoinWrappers.lambda(clazz);
        addCustomWrapper(unionWrapper);
        consumer.accept(unionWrapper);

        String sb = " UNION " + MTWrapperUtils.buildUnionSqlByWrapper(clazz, unionWrapper);

        if (Objects.isNull(unionSql)) {
            unionSql = SharedString.emptyString();
        }
        unionSql.setStringValue(unionSql.getStringValue() + sb);
        return typedThis;
    }


    /**
     * union
     * <p>
     * 推荐使用 unionAll(Class<U> clazz, Consumer<MPJLambdaWrapper<U>> consumer)
     * 例： wrapper.unionAll(UserDO.class, union -> union.selectAll(UserDO.class))
     *
     * @see #unionAll(Class, Consumer)
     * @deprecated union 不支持子查询
     */
    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    public MTLambdaWrapper<T> unionAll(MTLambdaWrapper<?>... wrappers) {
        StringBuilder sb = new StringBuilder();
        for (MTLambdaWrapper<?> wrapper : wrappers) {
            addCustomWrapper(wrapper);
            Class<?> entityClass = wrapper.getEntityClass();
            Assert.notNull(entityClass, "请使用 new MPJLambdaWrapper(主表.class) 或 JoinWrappers.lambda(主表.class) 构造方法");
            sb.append(" UNION ALL ").append(MTWrapperUtils.buildUnionSqlByWrapper(entityClass, wrapper));
        }
        if (Objects.isNull(unionSql)) {
            unionSql = SharedString.emptyString();
        }
        unionSql.setStringValue(unionSql.getStringValue() + sb);
        return typedThis;
    }

    /**
     * union
     * <p>
     * 例： wrapper.unionAll(UserDO.class, union -> union.selectAll(UserDO.class))
     *
     * @param clazz union语句的主表类型
     * @since 1.4.8
     */
    public <U> MTLambdaWrapper<T> unionAll(Class<U> clazz, Consumer<MTLambdaWrapper<U>> consumer) {
        MTLambdaWrapper<U> unionWrapper = MTJoinWrappers.lambda(clazz);
        addCustomWrapper(unionWrapper);
        consumer.accept(unionWrapper);

        String sb = " UNION ALL " + MTWrapperUtils.buildUnionSqlByWrapper(clazz, unionWrapper);

        if (Objects.isNull(unionSql)) {
            unionSql = SharedString.emptyString();
        }
        unionSql.setStringValue(unionSql.getStringValue() + sb);
        return typedThis;
    }

    private void addCustomWrapper(MTLambdaWrapper<?> wrapper) {
        if (Objects.isNull(wrapperIndex)) {
            wrapperIndex = new AtomicInteger(0);
        }
        int index = wrapperIndex.incrementAndGet();
        if (Objects.isNull(wrapperMap)) {
            wrapperMap = new HashMap<>();
        }
        String key = "ew" + index;
        wrapper.setParamAlias(wrapper.getParamAlias() + ".wrapperMap." + key);
        wrapperMap.put(key, wrapper);
    }


    @Override
    public void clear() {
        super.clear();
        selectDistinct = false;
        sqlSelect.toNull();
        selectColumns.clear();
        wrapperIndex = new AtomicInteger(0);
        if (Objects.nonNull(wrapperMap)) wrapperMap.clear();
        if (Objects.nonNull(unionSql)) unionSql.toEmpty();
        resultMapMybatisLabel.clear();
        ifAbsent = ConfigProperties.ifAbsent;
    }

    @Override
    public String getSqlSelect() {
        if (StringUtils.isBlank(sqlSelect.getStringValue()) && CollectionUtils.isNotEmpty(selectColumns)) {
            String s = selectColumns.stream().map(i -> {
                if (i.isStr()) {
                    return i.getColumn();
                }
                String prefix;
                if (i.isHasTableAlias()) {
                    prefix = i.getTableAlias();
                } else {
                    if (i.isLabel()) {
                        if (i.isHasTableAlias()) {
                            prefix = i.getTableAlias();
                        } else {
                            prefix = tableList.getPrefix(i.getIndex(), i.getClazz(), true);
                        }
                    } else {
                        prefix = tableList.getPrefix(i.getIndex(), i.getClazz(), false);
                    }
                }
                String str = prefix + StringPool.DOT + i.getColumn();
                if (i.isFunc()) {
                    SelectFunc.Arg[] args = i.getArgs();
                    if (Objects.isNull(args) || args.length == 0) {
                        return String.format(i.getFunc().getSql(), str) + Constant.AS + i.getAlias();
                    } else {
                        return String.format(i.getFunc().getSql(), Arrays.stream(args).map(arg -> {
                            String prefixByClass = tableList.getPrefixByClass(arg.getClazz());
                            Map<String, SelectCache> mapField = ColumnCache.getMapField(arg.getClazz());
                            SelectCache cache = mapField.get(arg.getProp());
                            return prefixByClass + StringPool.DOT + cache.getColumn();
                        }).toArray()) + Constant.AS + i.getAlias();
                    }
                } else {
                    return i.isHasAlias() ? (str + Constant.AS + i.getAlias()) : str;
                }
            }).collect(Collectors.joining(StringPool.COMMA));
            sqlSelect.setStringValue(s);
        }
        return sqlSelect.getStringValue();
    }

    @Override
    public String getUnionSql() {
        return Optional.ofNullable(unionSql).map(SharedString::getStringValue).orElse(StringPool.EMPTY);
    }
}
