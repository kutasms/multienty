package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.basic.PagedParameter;
import com.chia.multienty.core.domain.dto.WebLogDTO;
import lombok.Data;

@Data
public class WebLogListGetParameter extends PagedParameter<WebLogDTO> {
    private Long metaId;
    private Integer type;
}
