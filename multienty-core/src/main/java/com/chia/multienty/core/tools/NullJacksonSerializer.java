package com.chia.multienty.core.tools;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NullJacksonSerializer {

    /**
     * 处理数组
     */
    public static class NullArrayJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeEndArray();
        }
    }

    /**
     * 处理boolean类型
     */
    public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeBoolean(false);
        }
    }


    /**
     * 处理实体对象类型
     */
    public static class NullJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNull();
        }
    }
}
