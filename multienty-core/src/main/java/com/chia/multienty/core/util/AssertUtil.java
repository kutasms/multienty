package com.chia.multienty.core.util;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;

import java.util.Collection;

public class AssertUtil {

    public static void checkChanged(Object newVal, Object oldVal, String formatVal) {
        if(null != newVal && null != oldVal && newVal.equals(oldVal)) {
            throw new KutaRuntimeException(HttpResultEnum.ARG_NOT_CHANGED_PATTERN, formatVal);
        }
    }

    public static void checkNull(Object object, String formatVal) {
        if(null == object) {
            throw new KutaRuntimeException(HttpResultEnum.ARG_IS_NULL_PATTERN, formatVal);
        }
    }

    public static void checkNull(String formatVal, Object... objects) {
        for (Object object : objects) {
            if(null == object) {
                throw new KutaRuntimeException(HttpResultEnum.ARG_IS_NULL_PATTERN, formatVal);
            }
        }
    }

    public static void checkEmptyList(Collection<?> coll, String formatVal) {
        if(ListUtil.isEmpty(coll)) {
            throw new KutaRuntimeException(HttpResultEnum.COLLECTION_IS_EMPTY_PATTERN, formatVal);
        }
    }

    public static void checkError(boolean condition, String formatVal) {
        if(!condition) {
            throw new KutaRuntimeException(HttpResultEnum.ARG_ERROR_PATTERN, formatVal);
        }
    }

    public static void checkIllegal(boolean condition, String formatVal) {
        if(!condition) {
            throw new KutaRuntimeException(HttpResultEnum.ILLEGAL_ARG_PATTERN, formatVal);
        }
    }
}
