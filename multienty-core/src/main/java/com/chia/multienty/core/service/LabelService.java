package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.dto.LabelDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.pojo.Label;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface LabelService extends KutaBaseService<Label> {

    LabelDTO getDetail(LabelDetailGetParameter parameter);

    void delete(LabelDeleteParameter parameter);

    IPage<LabelDTO> getPage(LabelPageGetParameter parameter);

    List<Label> getLabels(List<Long> labelIds, SFunction<Label, ?>... columns);

    void review(LabelReviewParameter parameter);

    IPage<Label> recommend(LabelRecommendParameter parameter);

    void save(LabelSaveParameter parameter);

    void update(LabelUpdateParameter parameter);


}
