package com.chia.multienty.core.properties;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.Properties;

@Getter
public class AlgorithmProperties {
    private final String type;

    private final Properties props;

    public AlgorithmProperties(final String type, final Properties props) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(type), "Type is required.");
        this.type = type;
        this.props = null == props ? new Properties() : props;
    }
}
