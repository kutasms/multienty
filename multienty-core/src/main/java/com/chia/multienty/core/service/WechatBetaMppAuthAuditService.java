package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.WechatBetaMppAuthAudit;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatBetaMppAuthAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditUpdateParameter;
/**
 * <p>
 * 微信试用小程序转正审核单 服务类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
public interface WechatBetaMppAuthAuditService extends KutaBaseService<WechatBetaMppAuthAudit> {

    WechatBetaMppAuthAuditDTO getDetail(WechatBetaMppAuthAuditDetailGetParameter parameter);

    void delete(WechatBetaMppAuthAuditDeleteParameter parameter);

    IPage<WechatBetaMppAuthAuditDTO> getPage(WechatBetaMppAuthAuditPageGetParameter parameter);
    void enable(WechatBetaMppAuthAuditEnableParameter parameter);

    void disable(WechatBetaMppAuthAuditDisableParameter parameter);

    void save(WechatBetaMppAuthAuditSaveParameter parameter);

    void update(WechatBetaMppAuthAuditUpdateParameter parameter);

}
