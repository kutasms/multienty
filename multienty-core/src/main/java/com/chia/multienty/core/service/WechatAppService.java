package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.pojo.WechatApp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.strategy.pay.domain.PayType;

/**
 * <p>
 * 微信应用 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
public interface WechatAppService extends KutaBaseService<WechatApp> {

    WechatAppDTO getDetail(WechatAppDetailGetParameter parameter);

    WechatApp getBy(Long tenantId, String appId);

    boolean isWorking(Long tenantId, String appId);

    PayType getPayType(Long programId);

    WechatApp getByAppId(String appId);

    WechatApp getByPreAuthCode(String preAuthCode);

    WechatApp getByUniqueId(String uniqueId);

    WechatApp getWaiting(Long tenantId);

    void delete(WechatAppDeleteParameter parameter);

    IPage<WechatAppDTO> getPage(WechatAppPageGetParameter parameter);
    void enable(WechatAppEnableParameter parameter);

    void disable(WechatAppDisableParameter parameter);


    void setSubMchId(WechatAppSubMchIdSetParameter parameter);

    void save(WechatAppSaveParameter parameter);

    void update(WechatAppUpdateParameter parameter);

}
