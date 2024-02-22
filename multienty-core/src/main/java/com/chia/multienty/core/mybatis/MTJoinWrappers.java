package com.chia.multienty.core.mybatis;

import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.DeleteJoinWrapper;
import com.github.yulichang.wrapper.UpdateJoinWrapper;

public class MTJoinWrappers {
    /**
     * JoinWrappers.<UserDO>query()
     */
    public static <T> MPJQueryWrapper<T> query() {
        return new MPJQueryWrapper<>();
    }

    /**
     * JoinWrappers.query(User.class)
     */
    public static <T> MPJQueryWrapper<T> query(Class<T> clazz) {
        return new MPJQueryWrapper<>(clazz);
    }

    /**
     * JoinWrappers.query(user)
     */
    public static <T> MPJQueryWrapper<T> query(T entity) {
        return new MPJQueryWrapper<>(entity);
    }

    /**
     * JoinWrappers.<UserDO>lambda()
     */
    public static <T> MTLambdaWrapper<T> lambda() {
        return new MTLambdaWrapper<>();
    }



    /**
     * JoinWrappers.<UserDO>lambda("t")
     */
    public static <T> MTLambdaWrapper<T> lambda(String alias) {
        return new MTLambdaWrapper<>(alias);
    }

    /**
     * JoinWrappers.lambda(User.class)
     */
    public static <T> MTLambdaWrapper<T> lambda(Class<T> clazz) {
        return new MTLambdaWrapper<>(clazz);
    }

    /**
     * JoinWrappers.lambda("t", User.class)
     */
    public static <T> MTLambdaWrapper<T> lambda(String alias, Class<T> clazz) {
        return new MTLambdaWrapper<>(clazz, alias);
    }

    /**
     * JoinWrappers.lambda(user)
     */
    public static <T> MTLambdaWrapper<T> lambda(T entity) {
        return new MTLambdaWrapper<>(entity);
    }

    /**
     * JoinWrappers.lambda("t", user)
     */
    public static <T> MTLambdaWrapper<T> lambda(String alias, T entity) {
        return new MTLambdaWrapper<>(entity, alias);
    }

    /**
     * JoinWrappers.delete(User.class)
     */
    public static <T> DeleteJoinWrapper<T> delete(Class<T> clazz) {
        return new DeleteJoinWrapper<>(clazz);
    }

    /**
     * JoinWrappers.delete("t", User.class)
     */
    public static <T> DeleteJoinWrapper<T> delete(String alias, Class<T> clazz) {
        return new DeleteJoinWrapper<>(clazz, alias);
    }

    /**
     * JoinWrappers.update(User.class)
     */
    public static <T> UpdateJoinWrapper<T> update(Class<T> clazz) {
        return new UpdateJoinWrapper<>(clazz);
    }

    /**
     * JoinWrappers.update("t", User.class)
     */
    public static <T> UpdateJoinWrapper<T> update(String alias, Class<T> clazz) {
        return new UpdateJoinWrapper<>(clazz, alias);
    }
}
