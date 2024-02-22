package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WechatOfficialAccountDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WechatOfficialAccount;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountUpdateParameter;
/**
 * <p>
 * 微信公众号账户 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
public interface WechatOfficialAccountService extends KutaBaseService<WechatOfficialAccount> {

    WechatOfficialAccountDTO getDetail(WechatOfficialAccountDetailGetParameter parameter);

    void delete(WechatOfficialAccountDeleteParameter parameter);

    IPage<WechatOfficialAccountDTO> getPage(WechatOfficialAccountPageGetParameter parameter);
    void enable(WechatOfficialAccountEnableParameter parameter);

    void disable(WechatOfficialAccountDisableParameter parameter);

    void save(WechatOfficialAccountSaveParameter parameter);

    void update(WechatOfficialAccountUpdateParameter parameter);

}
