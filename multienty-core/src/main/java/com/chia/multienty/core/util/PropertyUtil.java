package com.chia.multienty.core.util;

import com.google.common.base.CaseFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtil {
    private static int springBootVersion = 1;

    static {
        try {
            Class.forName("org.springframework.boot.bind.RelaxedPropertyResolver");
        } catch (final ClassNotFoundException ignored) {
            springBootVersion = 2;
        }
    }

    /**
     * Whether environment contain properties with specified prefix.
     *
     * @param environment the environment context
     * @param prefix the prefix part of property key
     * @return true if contain, otherwise false
     */
    @SuppressWarnings("unchecked")
    public static boolean containPropertyPrefix(final Environment environment, final String prefix) {
        try {
            Map<String, Object> props = (Map<String, Object>) (1 == springBootVersion ? v1(environment, prefix, false) : v2(environment, prefix, Map.class));
            return !props.isEmpty();
            // CHECKSTYLE:OFF
        } catch (final Exception ex) {
            // CHECKSTYLE:ON
            return false;
        }
    }

    /**
     * Spring Boot 1.x is compatible with Spring Boot 2.x by Using Java Reflect.
     *
     * @param environment : the environment context
     * @param prefix : the prefix part of property key
     * @param targetClass : the target class type of result
     * @param <T> : refer to @param targetClass
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T handle(final Environment environment, final String prefix, final Class<T> targetClass) {
        return 1 == springBootVersion ? (T) v1(environment, prefix, true) : (T) v2(environment, prefix, targetClass);
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows(ReflectiveOperationException.class)
    private static Object v1(final Environment environment, final String prefix, final boolean handlePlaceholder) {
        Class<?> resolverClass = Class.forName("org.springframework.boot.bind.RelaxedPropertyResolver");
        Constructor<?> resolverConstructor = resolverClass.getDeclaredConstructor(PropertyResolver.class);
        Method getSubPropertiesMethod = resolverClass.getDeclaredMethod("getSubProperties", String.class);
        Object resolverObject = resolverConstructor.newInstance(environment);
        String prefixParameter = prefix.endsWith(".") ? prefix : prefix + ".";
        Method getPropertyMethod = resolverClass.getDeclaredMethod("getProperty", String.class);
        Map<String, Object> dataSourceProps = (Map<String, Object>) getSubPropertiesMethod.invoke(resolverObject, prefixParameter);
        Map<String, Object> result = new HashMap<>(dataSourceProps.size(), 1);
        for (Map.Entry<String, Object> entry : dataSourceProps.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (handlePlaceholder && value instanceof String && ((String) value).contains(PlaceholderConfigurerSupport.DEFAULT_PLACEHOLDER_PREFIX)) {
                String resolvedValue = (String) getPropertyMethod.invoke(resolverObject, prefixParameter + key);
                result.put(key, resolvedValue);
            } else {
                result.put(key, value);
            }
        }
        return result;
    }

    @SneakyThrows(ReflectiveOperationException.class)
    private static Object v2(final Environment environment, final String prefix, final Class<?> targetClass) {
        String dashedPrefix = toDashedForm(prefix);
        Class<?> binderClass = Class.forName("org.springframework.boot.context.properties.bind.Binder");
        Method getMethod = binderClass.getDeclaredMethod("get", Environment.class);
        Method bindMethod = binderClass.getDeclaredMethod("bind", String.class, Class.class);
        Object binderObject = getMethod.invoke(null, environment);
        String prefixParameter = dashedPrefix.endsWith(".") ? dashedPrefix.substring(0, prefix.length() - 1) : dashedPrefix;
        Object bindResultObject = bindMethod.invoke(binderObject, prefixParameter, targetClass);
        Method resultGetMethod = bindResultObject.getClass().getDeclaredMethod("get");
        return resultGetMethod.invoke(bindResultObject);
    }

    /**
     * Convert keys of map to camel case.
     *
     * @param dataSourceProps map to be converted
     * @return converted map
     */
    public static Map<String, Object> getCamelCaseKeys(final Map<String, Object> dataSourceProps) {
        Map<String, Object> result = new LinkedHashMap<>(dataSourceProps.size(), 1);
        for (Map.Entry<String, Object> entry : dataSourceProps.entrySet()) {
            String key = entry.getKey();
            result.put(key.contains("-") ? CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, key) : key, entry.getValue());
        }
        return result;
    }

    /**
     * Return the specified Java Bean property name in dashed form.
     *
     * @param name the source name
     * @return the dashed from
     */
    private static String toDashedForm(final String name) {
        StringBuilder result = new StringBuilder(name.length());
        boolean inIndex = false;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (inIndex) {
                result.append(ch);
                if (ch == ']') {
                    inIndex = false;
                }
            } else {
                if (ch == '[') {
                    inIndex = true;
                    result.append(ch);
                } else {
                    ch = (ch != '_') ? ch : '-';
                    if (Character.isUpperCase(ch) && result.length() > 0 && result.charAt(result.length() - 1) != '-') {
                        result.append('-');
                    }
                    result.append(Character.toLowerCase(ch));
                }
            }
        }
        return result.toString();
    }
}
