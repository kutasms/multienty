package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.WechatBetaMppRegisterAudit;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatBetaMppRegisterAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditUpdateParameter;
/**
 * <p>
 * 微信试用小程序注册审核单 服务类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
public interface WechatBetaMppRegisterAuditService extends KutaBaseService<WechatBetaMppRegisterAudit> {

    WechatBetaMppRegisterAuditDTO getDetail(WechatBetaMppRegisterAuditDetailGetParameter parameter);

    void delete(WechatBetaMppRegisterAuditDeleteParameter parameter);

    IPage<WechatBetaMppRegisterAuditDTO> getPage(WechatBetaMppRegisterAuditPageGetParameter parameter);
    void enable(WechatBetaMppRegisterAuditEnableParameter parameter);

    void disable(WechatBetaMppRegisterAuditDisableParameter parameter);

    void save(WechatBetaMppRegisterAuditSaveParameter parameter);

    void update(WechatBetaMppRegisterAuditUpdateParameter parameter);

}
