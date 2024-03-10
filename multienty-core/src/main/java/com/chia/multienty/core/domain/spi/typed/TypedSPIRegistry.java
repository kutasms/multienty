package com.chia.multienty.core.domain.spi.typed;

import com.chia.multienty.core.tools.MultientyServiceLoader;
import com.chia.multienty.core.domain.enums.InternalExceptionEnum;
import com.chia.multienty.core.domain.spi.lifecycle.InitializationProcessor;
import com.chia.multienty.core.exception.KutaRuntimeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TypedSPIRegistry {

    public static  <T extends TypedSPI> Optional<T> findRegisteredService(final Class<T> clazz, final String type){
        for(T item : MultientyServiceLoader.getInstances(clazz)) {
            if(isTypeMatched(type, item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public static <T extends TypedSPI> Optional<T> findRegisteredService(final Class<T> clazz,
                                                                         final String type, final Properties props) {
        for(T item : MultientyServiceLoader.getInstances(clazz)) {
            if(isTypeMatched(type, item)) {
                Properties stringizingProps = convertPropertiesItemToString(props);
                if(item instanceof InitializationProcessor) {
                    ((InitializationProcessor)item).init(stringizingProps);
                }
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    private static Properties convertPropertiesItemToString(final Properties props) {
        if (null == props) {
            return new Properties();
        }
        Properties result = new Properties();
        props.forEach((key, value) -> result.setProperty(key.toString(), null == value ? null : value.toString()));
        return result;
    }

    private static boolean isTypeMatched(final String type, final TypedSPI instance) {
        return instance.getType().equalsIgnoreCase(type) || instance.getTypeAliases().contains(type);
    }

    public static <T extends TypedSPI> T getRegisteredObject(final Class<T> clazz, final String type) {
        Optional<T> optional = findRegisteredService(clazz, type);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new KutaRuntimeException(InternalExceptionEnum.SERVICE_NOT_FOUND);
    }

    public static <T extends TypedSPI> T getRegisteredObject(final Class<T> clazz, final String type, final Properties props) {
        Optional<T> optional = findRegisteredService(clazz, type, props);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new KutaRuntimeException(InternalExceptionEnum.SERVICE_NOT_FOUND);
    }
}
