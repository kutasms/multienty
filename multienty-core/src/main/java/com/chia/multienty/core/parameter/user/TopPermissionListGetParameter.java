package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.basic.PagedParameter;
import com.chia.multienty.core.domain.dto.PermissionDTO;
import lombok.Data;

@Data
public class TopPermissionListGetParameter extends PagedParameter<PermissionDTO> {
    private Long owner;
}
