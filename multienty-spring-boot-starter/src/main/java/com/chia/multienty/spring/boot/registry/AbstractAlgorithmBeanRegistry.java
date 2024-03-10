package com.chia.multienty.spring.boot.registry;

import com.chia.multienty.core.algorithm.MultientyAlgorithmFactory;
import com.chia.multienty.core.domain.spi.MultientyAlgorithm;
import com.chia.multienty.core.properties.AlgorithmProperties;
import com.chia.multienty.core.tools.MultientyServiceLoader;
import com.chia.multienty.core.util.PropertyUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractAlgorithmBeanRegistry<T extends MultientyAlgorithm>
        implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor {
    private static final String POINT = ".";

    private static final String PROPS = "props";

    private static final String PROPS_SUFFIX = POINT + PROPS;

    private static final String TYPE_SUFFIX = ".type";
    private final Map<String, Properties> propMap = new HashMap<>();

    private final Environment environment;

    protected final void registerBean(final String prefix, final Class<T> algorithmClass, final BeanDefinitionRegistry registry) {
        if (!PropertyUtil.containPropertyPrefix(environment, prefix)) {
            return;
        }
        Map<String, Object> parameterMap = PropertyUtil.handle(environment, prefix, Map.class);
        Collection<String> algorithmNames = parameterMap.keySet().stream().map(key -> key.contains(POINT) ? key.substring(0, key.indexOf(POINT)) : key).collect(Collectors.toSet());

        Map<String, AlgorithmProperties> algorithmConfigs = createAlgorithmPropsMap(prefix, algorithmNames);
        MultientyServiceLoader.register(algorithmClass);

        for (Map.Entry<String, AlgorithmProperties> entry : algorithmConfigs.entrySet()) {
            AlgorithmProperties algorithmConfig = entry.getValue();
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MultientyAlgorithmFactory.createAlgorithm(algorithmConfig, algorithmClass).getClass());
            registry.registerBeanDefinition(entry.getKey(), builder.getBeanDefinition());
            propMap.put(entry.getKey(), algorithmConfig.getProps());
        }
    }

    private Map<String, AlgorithmProperties> createAlgorithmPropsMap(final String prefix, final Collection<String> algorithmNames) {
        Map<String, AlgorithmProperties> result = new LinkedHashMap<>(algorithmNames.size(), 1);
        for (String each : algorithmNames) {
            result.put(each, createAlgorithmProperties(prefix, each));
        }
        return result;
    }

    private AlgorithmProperties createAlgorithmProperties(final String prefix, final String algorithmName) {
        String type = environment.getProperty(String.join("", prefix, algorithmName, TYPE_SUFFIX));
        Properties props = getProperties(prefix, algorithmName);
        return new AlgorithmProperties(type, props);
    }

    private Properties getProperties(final String prefix, final String algorithmName) {
        String propsPrefix = String.join("", prefix, algorithmName, PROPS_SUFFIX);
        Properties result = new Properties();
        if (PropertyUtil.containPropertyPrefix(environment, propsPrefix)) {
            result.putAll(PropertyUtil.handle(environment, propsPrefix, Map.class));
        }
        return convertToStringTypedProperties(result);
    }

    private Properties convertToStringTypedProperties(final Properties props) {
        Properties result = new Properties();
        props.forEach((key, value) -> result.setProperty(key.toString(), null == value ? null : value.toString()));
        return result;
    }



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MultientyAlgorithm && propMap.containsKey(beanName)) {
            ((MultientyAlgorithm)bean).init(propMap.get(beanName));
        }
        return bean;
    }
}
