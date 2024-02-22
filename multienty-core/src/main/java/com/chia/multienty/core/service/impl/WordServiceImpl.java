package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.WordDTO;
import com.chia.multienty.core.mapper.WordMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Word;
import com.chia.multienty.core.service.WordService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.WordDetailGetParameter;
import com.chia.multienty.core.parameter.base.WordPageGetParameter;
import com.chia.multienty.core.parameter.base.WordDeleteParameter;
import com.chia.multienty.core.parameter.base.WordSaveParameter;
import com.chia.multienty.core.parameter.base.WordUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 关键词信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class WordServiceImpl extends KutaBaseServiceImpl<WordMapper, Word> implements WordService {


    @Override
    public WordDTO getDetail(WordDetailGetParameter parameter) {
        return selectJoinOne(WordDTO.class,
                        MPJWrappers.<Word>lambdaJoin().eq(Word::getId, parameter.getId()));
    }

    @Override
    public void delete(WordDeleteParameter parameter) {
        removeByIdTE(parameter.getId());
    }

    @Override
    public IPage<WordDTO> getPage(WordPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WordDTO.class,
                new KutaLambdaWrapper<Word>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getIds()), Word::getId, parameter.getIds())
        );
    }

    @Override
    public void save(WordSaveParameter parameter) {
        Word word = new Word();
        BeanUtils.copyProperties(parameter, word);
        saveTE(word);
        parameter.setId(word.getId());
    }

    @Override
    public void update(WordUpdateParameter parameter) {
        Word word = new Word();
        BeanUtils.copyProperties(parameter, word);
        updateByIdTE(word);
    }
}
