package com.chia.multienty.core.service.impl;


import com.chia.multienty.core.domain.dto.NoticeDTO;
import com.chia.multienty.core.mapper.NoticeMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Notice;
import com.chia.multienty.core.service.NoticeService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.NoticeDetailGetParameter;
import com.chia.multienty.core.parameter.base.NoticePageGetParameter;
import com.chia.multienty.core.parameter.base.NoticeDeleteParameter;
import com.chia.multienty.core.parameter.base.NoticeSaveParameter;
import com.chia.multienty.core.parameter.base.NoticeUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 系统通知信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class NoticeServiceImpl extends KutaBaseServiceImpl<NoticeMapper, Notice> implements NoticeService {


    @Override
    public NoticeDTO getDetail(NoticeDetailGetParameter parameter) {
        return selectJoinOne(NoticeDTO.class,
                        MPJWrappers.<Notice>lambdaJoin().eq(Notice::getNoticeId, parameter.getNoticeId()));
    }

    @Override
    public void delete(NoticeDeleteParameter parameter) {
        removeByIdTE(parameter.getNoticeId());
    }

    @Override
    public IPage<NoticeDTO> getPage(NoticePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), NoticeDTO.class,
                new MTLambdaWrapper<Notice>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getNoticeIds()), Notice::getNoticeId, parameter.getNoticeIds())
        );
    }

    @Override
    public void save(NoticeSaveParameter parameter) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(parameter, notice);
        saveTE(notice);
        parameter.setNoticeId(notice.getNoticeId());
    }

    @Override
    public void update(NoticeUpdateParameter parameter) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(parameter, notice);
        updateByIdTE(notice);
    }

}
