package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.WechatMppAuthTask;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatMppAuthTaskDTO;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskDisableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppAuthTaskUpdateParameter;
/**
 * <p>
 * 微信小程序认证任务 服务类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
public interface WechatMppAuthTaskService extends KutaBaseService<WechatMppAuthTask> {

    WechatMppAuthTaskDTO getDetail(WechatMppAuthTaskDetailGetParameter parameter);

    WechatMppAuthTask getBy(String appId);

    WechatMppAuthTask getBy(String wxTaskId, String tenantId);

    void delete(WechatMppAuthTaskDeleteParameter parameter);

    IPage<WechatMppAuthTaskDTO> getPage(WechatMppAuthTaskPageGetParameter parameter);
    void enable(WechatMppAuthTaskEnableParameter parameter);

    void disable(WechatMppAuthTaskDisableParameter parameter);

    void save(WechatMppAuthTaskSaveParameter parameter);

    void update(WechatMppAuthTaskUpdateParameter parameter);

}
