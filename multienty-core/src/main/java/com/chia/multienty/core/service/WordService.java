package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WordDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Word;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.WordDetailGetParameter;
import com.chia.multienty.core.parameter.base.WordPageGetParameter;
import com.chia.multienty.core.parameter.base.WordDeleteParameter;
import com.chia.multienty.core.parameter.base.WordSaveParameter;
import com.chia.multienty.core.parameter.base.WordUpdateParameter;

/**
 * <p>
 * 关键词信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface WordService extends KutaBaseService<Word> {

    WordDTO getDetail(WordDetailGetParameter parameter);

    void delete(WordDeleteParameter parameter);

    IPage<WordDTO> getPage(WordPageGetParameter parameter);

    void save(WordSaveParameter parameter);

    void update(WordUpdateParameter parameter);


}
