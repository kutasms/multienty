package com.chia.multienty.core.domain.spi.typed;

import java.util.Collection;
import java.util.Collections;

public interface TypedSPI {
    /**
     * Get type.
     *
     * @return type
     */
    default String getType() {
        return "";
    }

    /**
     * Get type aliases.
     *
     * @return type aliases
     */
    default Collection<String> getTypeAliases() {
        return Collections.emptyList();
    }
}
