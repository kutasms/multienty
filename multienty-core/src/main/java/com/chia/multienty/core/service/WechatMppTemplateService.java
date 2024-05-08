package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatMppTemplateDTO;
import com.chia.multienty.core.domain.wechat.WxMppTemplateType;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WechatMppTemplate;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplatePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateUpdateParameter;

import java.util.List;

/**
 * <p>
 * 微信小程序模版 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
public interface WechatMppTemplateService extends KutaBaseService<WechatMppTemplate> {

    WechatMppTemplateDTO getDetail(WechatMppTemplateDetailGetParameter parameter);

    List<WechatMppTemplate> getList(Long programId);

    void delete(WechatMppTemplateDeleteParameter parameter);

    void batchDelete(Long programId);

    IPage<WechatMppTemplateDTO> getPage(WechatMppTemplatePageGetParameter parameter);
    void enable(WechatMppTemplateEnableParameter parameter);

    void disable(WechatMppTemplateDisableParameter parameter);

    void save(WechatMppTemplateSaveParameter parameter);

    void update(WechatMppTemplateUpdateParameter parameter);

    WechatMppTemplate getOne(Long tenantId, Long programId, WxMppTemplateType templateType);
}
