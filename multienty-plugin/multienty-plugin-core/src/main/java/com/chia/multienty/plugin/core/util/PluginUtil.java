package com.chia.multienty.plugin.core.util;

import cn.hutool.core.lang.JarClassLoader;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.plugin.core.constants.PluginConstants;
import com.chia.multienty.plugin.core.metadata.IMultientyPlugin;
import com.chia.multienty.plugin.core.properties.YamlMultientyPluginProperties;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginUtil {

    /**
     * 热注册插件，无须重启应用
     * @param path plugin目录路径
     * @param jarName jar文件名称
     * @param packageName 包名
     * @param className 类名
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static IMultientyPlugin registerPlugin(String path, String jarName,String packageName, String className)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazzInterface = IMultientyPlugin.class;
        Path finalPath = Paths.get(path, jarName);
        File file = finalPath.toFile();
        JarClassLoader.loadJarToSystemClassLoader(file);
        String finalClassName = packageName + "." + className;
        Class<?> clazz = Class.forName(finalClassName);
        if(clazzInterface.isAssignableFrom(clazz)
                && !clazz.isInterface()
                && !Modifier.isAbstract(clazz.getModifiers())) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            BeanDefinition beanDefinition = builder.getBeanDefinition();
            IMultientyPlugin o = (IMultientyPlugin) clazz.newInstance();
            String finalName = o.isOverrideNameOnRegister() ? PluginConstants.PREFIX + o.getName() : o.getName();
            AnnotationConfigServletWebApplicationContext applicationContext = SpringUtil.getAnnotationConfigServAppContext();
            applicationContext.registerBeanDefinition(finalName, beanDefinition);
            return o;
        }
        return null;
    }
    /**
     * 热注册插件，无须重启应用<br/>
     * <b>使用nacos配置中的spring.multienty.plugin.class-path作为路径</b>
     * @param jarName jar文件名称
     * @param packageName 包名
     * @param className 类名
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static IMultientyPlugin registerPlugin(String jarName,String packageName, String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        YamlMultientyPluginProperties properties = SpringUtil.getBean(YamlMultientyPluginProperties.class);
        return registerPlugin(properties.getClassPath(), jarName, packageName, className);
    }
    /**
     * 指定包前缀和类pattern注册bean
     * @param basePackage 基础包
     * @param classPattern 类pattern
     * @param registry bean注册器
     */
    public static void registerBeanForSpecificPackageAndClassPattern(final String basePackage,
                                                                     final String classPattern,
                                                                     BeanDefinitionRegistry registry) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(basePackage) + classPattern;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(className);
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                BeanDefinition beanDefinition = builder.getBeanDefinition();
                registry.registerBeanDefinition(clazz.getName(), beanDefinition);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
