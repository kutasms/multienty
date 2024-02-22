package com.chia.multienty.core.pojo;

import com.chia.multienty.core.domain.enums.SymbolEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class KutaBaseEntity implements Serializable {
    public String getCacheKey(Object key) {
        return this.getClass().getSimpleName() + SymbolEnum.UNDER_LINE.getCode() + key.toString();
    }
}
