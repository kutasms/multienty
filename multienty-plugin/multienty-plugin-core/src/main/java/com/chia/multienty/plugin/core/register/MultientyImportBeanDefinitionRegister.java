package com.chia.multienty.plugin.core.register;

import cn.hutool.core.lang.JarClassLoader;
import com.chia.multienty.plugin.core.constants.PluginConstants;
import com.chia.multienty.plugin.core.metadata.IMultientyPlugin;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class MultientyImportBeanDefinitionRegister
        implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    private final String CLASS_PATH_CFG_KEY = "spring.multienty.plugin.class-path";


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String classPath = environment.getProperty(CLASS_PATH_CFG_KEY);
        Class<?> clazzInterface = IMultientyPlugin.class;
        JarClassLoader.loadJarToSystemClassLoader(new File(classPath));
        File pluginDir = new File(classPath);
        File[] files = pluginDir.listFiles();
        for (File file : files) {
            if(file.isDirectory()) {
                // 目录不加载
            } else {
                try {
                    JarFile jarFile = new JarFile(file);
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry jarEntry = entries.nextElement();
                        if(jarEntry.getName().endsWith(".class")) {
                            String typeName = jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace('/', '.');
                            Class<?> clazz = Class.forName(typeName);
                            if(clazzInterface.isAssignableFrom(clazz)
                                    && !clazz.isInterface()
                                    && !Modifier.isAbstract(clazz.getModifiers())) {
                                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                                BeanDefinition beanDefinition = builder.getBeanDefinition();
                                IMultientyPlugin o = (IMultientyPlugin) clazz.newInstance();
                                String finalName = o.isOverrideNameOnRegister() ? PluginConstants.PREFIX + o.getName() : o.getName();
                                registry.registerBeanDefinition(finalName, beanDefinition);
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
