package com.chia.multienty.core.domain.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "WrappedPage",description = "包装的Page对象")
public class WrappedPage<TD, TE> {
    private IPage<TE> page;
    private TD data;
}
