package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.basic.PagedParameter;

public interface ComponentService{

    IPage<String> getIcons(PagedParameter<String> parameter);
}