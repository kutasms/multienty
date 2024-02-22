package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WechatMppNickNameAudit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditUpdateParameter;
/**
 * <p>
 * 微信小程序昵称审核单 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */
public interface WechatMppNickNameAuditService extends KutaBaseService<WechatMppNickNameAudit> {

    WechatMppNickNameAuditDTO getDetail(WechatMppNickNameAuditDetailGetParameter parameter);

    void delete(WechatMppNickNameAuditDeleteParameter parameter);

    IPage<WechatMppNickNameAuditDTO> getPage(WechatMppNickNameAuditPageGetParameter parameter);
    void enable(WechatMppNickNameAuditEnableParameter parameter);

    void disable(WechatMppNickNameAuditDisableParameter parameter);

    WechatMppNickNameAudit getByNickName(String nickName);

    void save(WechatMppNickNameAuditSaveParameter parameter);

    void update(WechatMppNickNameAuditUpdateParameter parameter);

}
