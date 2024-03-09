package com.chia.multienty.core.fusion.flyway.condition;

import com.chia.multienty.core.util.PropertyUtil;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class FlywayExtensionCondition extends SpringBootCondition {
    private static final String FLYWAY_EXT_PREFIX = "spring.flyway.extensions";
    @Override
    public ConditionOutcome getMatchOutcome(final ConditionContext conditionContext, final AnnotatedTypeMetadata annotatedTypeMetadata) {
        return PropertyUtil.containPropertyPrefix(conditionContext.getEnvironment(), FLYWAY_EXT_PREFIX)
                ? ConditionOutcome.match()
                : ConditionOutcome.noMatch("Can't find extension configuration of flyway in local file.");
    }
}
