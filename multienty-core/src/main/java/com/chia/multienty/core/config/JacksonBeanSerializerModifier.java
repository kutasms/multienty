package com.chia.multienty.core.config;

import com.chia.multienty.core.tools.NullJacksonSerializer;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *     Null值的各类型转化配置
 * </p>
 */
public class JacksonBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, 
                                                     BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {

        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            // 判断字段的类型，如果是数组或集合则注册nullSerializer
            if (isArrayType(writer)) {
                // 给writer注册一个自己的nullSerializer   
                writer.assignNullSerializer(new NullJacksonSerializer.NullArrayJsonSerializer());
            }
            if (isStringType(writer) || isDateType(writer) || isNumberType(writer)) {
                writer.assignNullSerializer(new NullJacksonSerializer.NullJsonSerializer());
            }
            if(isBooleanType(writer)) {
                writer.assignNullSerializer(new NullJacksonSerializer.NullBooleanJsonSerializer());
            }
        }
        return beanProperties;
    }

    /**
     * 是否是数组
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }

    private boolean isNumberType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是String
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz) || String.class.equals(clazz);
    }

    private boolean isDateType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        boolean a = Date.class.isAssignableFrom(clazz);
        boolean b = LocalDateTime.class.isAssignableFrom(clazz);
        boolean c = LocalDate.class.isAssignableFrom(clazz);
        return a || b || c;
    }

    /**
     * 是否是boolean
     */
    private boolean isBooleanType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }


}
