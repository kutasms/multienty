package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.WechatMppRegisterAudit;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditUpdateParameter;
/**
 * <p>
 * 微信小程序注册审核单 服务类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
public interface WechatMppRegisterAuditService extends KutaBaseService<WechatMppRegisterAudit> {

    WechatMppRegisterAuditDTO getDetail(WechatMppRegisterAuditDetailGetParameter parameter);

    void delete(WechatMppRegisterAuditDeleteParameter parameter);

    IPage<WechatMppRegisterAuditDTO> getPage(WechatMppRegisterAuditPageGetParameter parameter);
    void enable(WechatMppRegisterAuditEnableParameter parameter);

    void disable(WechatMppRegisterAuditDisableParameter parameter);

    void save(WechatMppRegisterAuditSaveParameter parameter);

    WechatMppRegisterAuditDTO getBy(String name, String code, String legalPersonaWechat, String legalPersonaName);

    void update(WechatMppRegisterAuditUpdateParameter parameter);

}
