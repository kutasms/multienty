package com.chia.multienty.spring.boot.condition;

import org.apache.shardingsphere.spring.boot.util.PropertyUtil;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MultientySpringBootCondition  extends SpringBootCondition {
    private static final String MULTI_TENANT_PREFIX = "spring.multienty";
    @Override
    public ConditionOutcome getMatchOutcome(final ConditionContext conditionContext, final AnnotatedTypeMetadata annotatedTypeMetadata) {
        return PropertyUtil.containPropertyPrefix(conditionContext.getEnvironment(), MULTI_TENANT_PREFIX)
                ? ConditionOutcome.match()
                : ConditionOutcome.noMatch("Can't find multienty configuration in local file.");
    }
}
