package com.chia.multienty.core.fusion.sharding.registry;

import com.chia.multienty.core.util.SpringUtil;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithm;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmFactory;
import org.apache.shardingsphere.spring.boot.registry.AbstractAlgorithmProvidedBeanRegistry;
import org.apache.shardingsphere.spring.boot.util.PropertyUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractMTAlgorithmProvidedBeanRegistry<T extends ShardingSphereAlgorithm> extends
        AbstractAlgorithmProvidedBeanRegistry<T> {
    private final Environment environment;

    private static final String POINT = ".";

    private static final String PROPS = "props";

    private static final String PROPS_SUFFIX = POINT + PROPS;

    private static final String TYPE_SUFFIX = ".type";

    private final Map<String, Properties> propsMap = new HashMap<>();

    protected AbstractMTAlgorithmProvidedBeanRegistry(Environment environment) {
        super(environment);
        this.environment = environment;
    }

    protected final void alterBean(final String prefix, final Class<T> algorithmClass, final BeanDefinitionRegistry registry) {

        if (!PropertyUtil.containPropertyPrefix(environment, prefix)) {
            return;
        }
        Map<String, Object> parameterMap = PropertyUtil.handle(environment, prefix, Map.class);
        Collection<String> algorithmNames = parameterMap.keySet().stream().map(key -> key.contains(POINT) ? key.substring(0, key.indexOf(POINT)) : key).collect(Collectors.toSet());
        Map<String, AlgorithmConfiguration> algorithmConfigs = createAlgorithmConfigurations(prefix, algorithmNames);
        for (Map.Entry<String, AlgorithmConfiguration> entry : algorithmConfigs.entrySet()) {
            AlgorithmConfiguration algorithmConfig = entry.getValue();
            if(SpringUtil.getApplicationContext().containsBean(entry.getKey())) {
                ShardingSphereAlgorithm algorithm = SpringUtil.getBean(entry.getKey(), ShardingSphereAlgorithm.class);
                synchronized (algorithm) {
                    algorithm.init(algorithmConfig.getProps());
                    propsMap.put(entry.getKey(), algorithmConfig.getProps());
                }
            }
            else {
                ShardingSphereAlgorithm algorithm = ShardingSphereAlgorithmFactory.createAlgorithm(algorithmConfig, algorithmClass);
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(algorithm.getClass());
                registry.registerBeanDefinition(entry.getKey(), builder.getBeanDefinition());
                propsMap.put(entry.getKey(), algorithmConfig.getProps());
            }

        }
    }

    private Map<String, AlgorithmConfiguration> createAlgorithmConfigurations(final String prefix, final Collection<String> algorithmNames) {
        Map<String, AlgorithmConfiguration> result = new LinkedHashMap<>(algorithmNames.size(), 1);
        for (String each : algorithmNames) {
            result.put(each, createAlgorithmConfiguration(prefix, each));
        }
        return result;
    }

    private AlgorithmConfiguration createAlgorithmConfiguration(final String prefix, final String algorithmName) {
        String type = environment.getProperty(String.join("", prefix, algorithmName, TYPE_SUFFIX));
        Properties props = getProperties(prefix, algorithmName);
        return new AlgorithmConfiguration(type, props);
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
}
