package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.dto.LabelDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mapper.LabelMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.pojo.Label;
import com.chia.multienty.core.service.LabelService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class LabelServiceImpl extends KutaBaseServiceImpl<LabelMapper, Label> implements LabelService {


    @Override
    public LabelDTO getDetail(LabelDetailGetParameter parameter) {
        return selectJoinOne(LabelDTO.class,
                        MPJWrappers.<Label>lambdaJoin().eq(Label::getLabelId, parameter.getLabelId()));
    }

    @Override
    public void delete(LabelDeleteParameter parameter) {
        removeByIdTE(parameter.getLabelId());
    }

    @Override
    public IPage<LabelDTO> getPage(LabelPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), LabelDTO.class,
                new KutaLambdaWrapper<Label>()
                        .solveGenericParameters(parameter)

                        .in(!ListUtil.isEmpty(parameter.getLabelIds()), Label::getLabelId, parameter.getLabelIds())
        );
    }

    @Override
    public List<Label> getLabels(List<Long> labelIds, SFunction<Label, ?>... columns) {
        return list(new LambdaQueryWrapper<Label>()
                .select(columns)
                .eq(Label::getStatus, StatusEnum.NORMAL.getCode())
                .in(Label::getLabelId, labelIds));
    }

    @Override
    public void review(LabelReviewParameter parameter) {
        Label label = getBy(parameter.getLabelId(),Label::getLabelId, Label::getStatus);
        if(label == null) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DATA_EMPTY);
        }
        if(!label.getStatus().equals(StatusEnum.WAITING.getCode())) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_STATUS_ERROR);
        }
        if(parameter.getStatus().equals(StatusEnum.FAILURE) && parameter.getFailReason() == null) {
            throw new KutaRuntimeException(HttpResultEnum.REVIEW_FAIL_NEED_REASON);
        }
        BeanUtils.copyProperties(parameter, label);
        updateByIdTE(label);
    }

    @Override
    public IPage<Label> recommend(LabelRecommendParameter parameter) {
        if(parameter.getPageSize() > 100) {
            throw new KutaRuntimeException(HttpResultEnum.PAGE_SIZE_EXCEEDED_50);
        }
        return selectJoinListPage(parameter.getPageObj(), Label.class,
                new KutaLambdaWrapper<Label>()
                        .likeRight(Label::getLabel, parameter.getInputKeywords())
        );
    }

    @Override
    public void save(LabelSaveParameter parameter) {
        Label label = new Label();
        BeanUtils.copyProperties(parameter, label);
        label.setStatus(StatusEnum.WAITING.getCode());
        saveTE(label);
        parameter.setLabelId(label.getLabelId());
    }

    @Override
    public void update(LabelUpdateParameter parameter) {
        Label label = new Label();
        BeanUtils.copyProperties(parameter, label);
        updateByIdTE(label);
    }
}
