package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.log.*;
import com.chia.multienty.core.pojo.RabbitLog;

/**
 * <p>
 * Rabbit MQ日志信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface RabbitLogService extends KutaBaseService<RabbitLog> {

    RabbitLog getByKey(String key);

    boolean remove(String routingKey, Long metaId, String boType);

    void finish(String key);

    RabbitLogDTO getDetail(RabbitLogDetailGetParameter parameter);

    void delete(RabbitLogDeleteParameter parameter);

    IPage<RabbitLogDTO> getPage(RabbitLogPageGetParameter parameter);

    void save(RabbitLogSaveParameter parameter);

    void update(RabbitLogUpdateParameter parameter);
}
