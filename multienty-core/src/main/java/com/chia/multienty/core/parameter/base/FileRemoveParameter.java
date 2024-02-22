package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.annotation.LogMetaId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FileRemoveParameter {
    @LogMetaId
    private List<Long> fileIds;
}
