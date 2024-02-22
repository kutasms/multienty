package com.chia.multienty.core.config;

import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
@ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@Slf4j
@RequiredArgsConstructor
public class JacksonConfig {



    private final YamlMultiTenantProperties properties;


    @Bean
    public ObjectMapper objectMapper() {

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer());

        javaTimeModule.addSerializer(LocalDate.class, localDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer());

        return new ObjectMapper()
                .setLocale(Locale.CHINA)
                .setTimeZone(TimeZone.getTimeZone(ZoneId.of(properties.getJackson().getTimeZone())))
                .registerModule(javaTimeModule);
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(properties.getJackson().getDateFormat()).withZone(ZoneId.of(properties.getJackson().getTimeZone())));
    }

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(properties.getJackson().getDateFormat()).withZone(ZoneId.of(properties.getJackson().getTimeZone())));
    }

    @Bean
    public LocalDateDeserializer localDateDeserializer() {
        return new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of(properties.getJackson().getTimeZone())));
    }

    @Bean
    public LocalDateSerializer localDateSerializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of(properties.getJackson().getTimeZone())));
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        final ObjectMapper objectMapper = builder.build();
        SimpleModule simpleModule = new SimpleModule();
        // Long 转为 String 防止 js 丢失精度
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(LocalDateTime.class, localDateTimeSerializer());
        simpleModule.addDeserializer(LocalDateTime.class,localDateTimeDeserializer());
        simpleModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of(properties.getJackson().getTimeZone()))));
        simpleModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of(properties.getJackson().getTimeZone()))));
        objectMapper.registerModule(simpleModule);
//        registerDateSerializer(objectMapper);
        // 忽略 transient 关键词属性
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    private void registerDateSerializer(ObjectMapper mapper) {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer());
        javaTimeModule.addSerializer(LocalDate.class, localDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer());
        mapper.registerModule(javaTimeModule);

    }
}
