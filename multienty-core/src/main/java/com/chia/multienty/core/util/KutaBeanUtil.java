package com.chia.multienty.core.util;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class KutaBeanUtil {

    /**
     * 获取指定类型对象中所有值为null的字段数组
     * @param clazz
     * @param obj
     * @return
     */
    public static String[] getNullProperties(Class<?> clazz, Object obj) {
        List<String> properties = new ArrayList<>();
        try{
            for(Field field: clazz.getDeclaredFields()){
                field.setAccessible(true);
                if(field.get(obj) == null) {
                    properties.add(field.getName());
                }
            }
        }
        catch (Exception e) {
            return null;
        }
        String[] pps = new String[properties.size()];
        return properties.toArray(pps);
    }

    public static <T,R> List<R> convert(List<T> ts, Class<R> clazz) throws InstantiationException, IllegalAccessException {
        Assert.assertNotNull(ts);
        List<R> rs = new ArrayList<>();
        for(T t : ts) {
            R r = clazz.newInstance();
            BeanUtils.copyProperties(t, r);
            rs.add(r);
        }
        return rs;
    }
    /**
     * 将map装换为javabean对象
     * @param map
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        String s = JSONObject.toJSONString(map);
        return JSONObject.parseObject(s, clazz);
    }

    /**
     * bean转Map
     * @param <T>
     * @return
     */
    public static <T> T mapToBeanByJackson(Map<String, Object> map, Class<T> clazz){
        ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * bean转Map
     * @param <T>
     * @return
     */
    public static <T> Map<String,Object> bean2Map(T bean){
        if(!bean.getClass().equals(Map.class)) {
            String s = JSONObject.toJSONString(bean);
            return (Map<String,Object>)JSONObject.parse(s);
        }
        else {
            return (Map<String,Object>)bean;
        }
    }

    /**
     * bean转Map
     * @param <T>
     * @return
     */
    public static <T> Map<String,Object> bean2MapByJackson(T bean){
        if(!bean.getClass().equals(Map.class)) {
            ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
            return objectMapper.convertValue(bean, Map.class);
        }
        else {
            return (Map<String,Object>)bean;
        }
    }

    public static boolean hasField(Class clazz, String fieldName) {
        Field[] fields = getAllDeclaredFields(clazz);
        for(Field f : fields) {
            if(fieldName.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasField(Field[] fields, String fieldName) {
        for(Field f : fields) {
            if(fieldName.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取所有字段（包含父类所有字段）
     * @param clazz
     * @return
     * @param
     */
    public static Field[] getAllDeclaredFields(Class<?> clazz) {
        List<Field[]> array = new ArrayList<>();
        while (clazz != null) {
            array.add(clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        int count = array.stream().map(m->m.length).reduce((a,b)-> a+b).get();
        Field[] fields = new Field[count];
        int index = 0;
        for (Field[] child : array) {
            for (Field field : child) {
                fields[index++] = field;
            }
        }
        return fields;
    }

    /**
     * 是否包含所有字段
     * @param clazz 类型
     * @param fieldNames 字段名称数组
     * @return
     */
    public static boolean hasFields(Class clazz, String... fieldNames) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> columns = new ArrayList<>();
        for(Field f : fields) {
            columns.add(f.getName());
        }
        for (String fieldName : fieldNames) {
            if(!columns.contains(fieldName)) {
                return false;
            }
        }
        return true;
    }

    public static Object getFieldVal(Object obj, String fieldName) {
        Field[] fields = getAllDeclaredFields(obj.getClass());
        return getFieldVal(obj, fields, fieldName);
    }

    public static Object getFieldVal(Object obj, Field[] fields, String fieldName) {
        if(fields == null) {
            fields = getAllDeclaredFields(obj.getClass());
        }
        for(Field f : fields) {
            if(fieldName.equals(f.getName())) {
                f.setAccessible(true);
                Object val = null;
                try {
                    val = f.get(obj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } finally {
                    f.setAccessible(false);
                }
                return val;
            }
        }
        return null;
    }
}
