package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.NoticeDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Notice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.NoticeDetailGetParameter;
import com.chia.multienty.core.parameter.base.NoticePageGetParameter;
import com.chia.multienty.core.parameter.base.NoticeDeleteParameter;
import com.chia.multienty.core.parameter.base.NoticeSaveParameter;
import com.chia.multienty.core.parameter.base.NoticeUpdateParameter;

/**
 * <p>
 * 系统通知信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface NoticeService extends KutaBaseService<Notice> {

    NoticeDTO getDetail(NoticeDetailGetParameter parameter);

    void delete(NoticeDeleteParameter parameter);


    IPage<NoticeDTO> getPage(NoticePageGetParameter parameter);

    void save(NoticeSaveParameter parameter);

    void update(NoticeUpdateParameter parameter);


}
