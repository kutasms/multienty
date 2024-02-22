package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatMppCodeAuditDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WechatMppCodeAudit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditUpdateParameter;
/**
 * <p>
 * 微信小程序代码审核单 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */
public interface WechatMppCodeAuditService extends KutaBaseService<WechatMppCodeAudit> {

    WechatMppCodeAuditDTO getDetail(WechatMppCodeAuditDetailGetParameter parameter);

    WechatMppCodeAudit getByAppId(String appId);

    void delete(WechatMppCodeAuditDeleteParameter parameter);

    IPage<WechatMppCodeAuditDTO> getPage(WechatMppCodeAuditPageGetParameter parameter);
    void enable(WechatMppCodeAuditEnableParameter parameter);

    void disable(WechatMppCodeAuditDisableParameter parameter);

    void save(WechatMppCodeAuditSaveParameter parameter);

    void update(WechatMppCodeAuditUpdateParameter parameter);

}
