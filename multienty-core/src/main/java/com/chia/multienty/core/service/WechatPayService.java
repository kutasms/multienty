package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatPayDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WechatPay;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatPayDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatPaySaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayUpdateParameter;
/**
 * <p>
 * 微信支付 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
public interface WechatPayService extends KutaBaseService<WechatPay> {

    WechatPayDTO getDetail(WechatPayDetailGetParameter parameter);

    WechatPay getBy(Long tenantId, Long programId);

    WechatPayDTO getDTOBy(Long tenantId);

    void delete(WechatPayDeleteParameter parameter);

    IPage<WechatPayDTO> getPage(WechatPayPageGetParameter parameter);
    void enable(WechatPayEnableParameter parameter);

    void disable(WechatPayDisableParameter parameter);

    void save(WechatPaySaveParameter parameter);

    void update(WechatPayUpdateParameter parameter);

}
