package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.mapper.SearchEntityMapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.SearchEntity;
import com.chia.multienty.core.service.SearchEntityService;
import org.springframework.stereotype.Service;

@Service
public class SearchEntityServiceImpl
        extends KutaBaseServiceImpl<SearchEntityMapper, SearchEntity>
        implements SearchEntityService {
}
