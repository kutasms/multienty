package com.chia.multienty.core.dubbo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.pojo.*;
import com.chia.multienty.core.rabbitmq.RabbitPushResult;

import java.io.IOException;
import java.util.List;

public interface DubboMultientyService {
    boolean saveWebLog(WebLog webLog);
    TenantDTO getTenant(TenantDetailGetParameter parameter);
    IPage<RabbitLogDTO> getRabbitLogPage(RabbitLogPageGetParameter parameter);

    void batchUpdateRabbitLogStatus(StatusEnum status, List<Long> rids);

    IPage<SettingDTO> getSettingPage(SettingPageGetParameter parameter);
    List<Setting> getSettings(Integer appId, Long owner);
    Setting getSetting(Integer appId, Long owner, String name);
    void addSchedulingJob(SchedulingJob job);
    void removeSchedulingJob(Long jobId);

    RabbitPushResult sendRabbitDelayMsg(Object object, String routingKey, long delayTime, String boType, Long metaId, boolean idempotent);

    RabbitPushResult sendRabbitImmediateMsg(Object object, String routingKey, String boType, Long metaId, boolean idempotent);

    RabbitLog getRabbitLogByKey(String key);

    boolean removeRabbitLog(Long metaId, String boType, String routingKey);

    void updateRabbitLog(RabbitLog rabbitLog);

    void saveOrUpdateRabbitLog(RabbitLog rabbitLog);

    void finishRabbitLog(String key);

    void updateFilesMetaId(List<UploadedFileDTO> files);

    void deleteFile(Long fileId) throws IOException;

    List<Label> getLabels(List<Long> labelIds, SFunction<Label, ?>... columns);
}
