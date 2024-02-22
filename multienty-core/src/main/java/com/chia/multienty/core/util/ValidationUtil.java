package com.chia.multienty.core.util;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * 参数验证工具类
 */
public class ValidationUtil {
    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * 参数校验
     * @param parameter 需要校验的参数
     */
    public static void validate(Object parameter) {
        Validator validator = VALIDATOR_FACTORY.getValidator();
        Set<ConstraintViolation<Object>> result = validator.validate(parameter);
        if(ListUtil.isEmpty(result)) {
           return;
        }
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<Object> violation : result) {
            sb.append(violation.getMessage()).append(";");
        }
        throw new KutaRuntimeException(HttpResultEnum.METHOD_ARG_NOT_VALID, sb.toString());
    }

    /**
     * 参数校验【支持分组校验】
     * @param parameter 需要校验的参数
     * @param clazz 具体的分组
     */
    public static void validate(Object parameter, Class<?> clazz) {
        Validator validator = VALIDATOR_FACTORY.getValidator();
        Set<ConstraintViolation<Object>> result = validator.validate(parameter, clazz);
        if(ListUtil.isEmpty(result)) {
            return;
        }

        Set<String> errorMsgSet = new HashSet<>();
        for (ConstraintViolation<Object> violation : result) {
            errorMsgSet.add(violation.getMessage());
        }
        throw new KutaRuntimeException(HttpResultEnum.METHOD_ARG_NOT_VALID, String.join(",", errorMsgSet));
    }

    private ValidationUtil() {}
}
