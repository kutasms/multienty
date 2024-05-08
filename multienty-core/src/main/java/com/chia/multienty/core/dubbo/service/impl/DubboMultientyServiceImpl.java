package com.chia.multienty.core.dubbo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.parameter.base.FileRemoveParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobDeleteParameter;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.pojo.*;
import com.chia.multienty.core.rabbitmq.DelayedMessageProducer;
import com.chia.multienty.core.rabbitmq.ImmediateMessageProducer;
import com.chia.multienty.core.rabbitmq.RabbitPushResult;
import com.chia.multienty.core.service.*;
import com.chia.multienty.core.strategy.file.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@DubboService
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class DubboMultientyServiceImpl implements DubboMultientyService {
    private final WebLogService webLogService;
    private final TenantService tenantService;
    private final RabbitLogService rabbitLogService;
    private final SettingService settingService;
    private final SchedulingJobService schedulingJobService;
    private final DelayedMessageProducer delayedMessageProducer;
    private final ImmediateMessageProducer immediateMessageProducer;
    private final UploadedFileService uploadedFileService;
    private final LabelService labelService;

    @Autowired(required = false)
    private FileUploadService fileUploadService;

    @Override
    public boolean saveWebLog(WebLog webLog) {
        return webLogService.save(webLog);
    }

    @Override
    public TenantDTO getTenant(TenantDetailGetParameter parameter) {
        return tenantService.getDetail(parameter);
    }

    @Override
    public IPage<RabbitLogDTO> getRabbitLogPage(RabbitLogPageGetParameter parameter) {
        return rabbitLogService.getPage(parameter);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void batchUpdateRabbitLogStatus(StatusEnum status, List<Long> rids) {
        rabbitLogService.batchUpdateStatus(status, rids);
    }

    @Override
    public IPage<SettingDTO> getSettingPage(SettingPageGetParameter parameter) {
        return settingService.getPage(parameter);
    }

    @Override
    public List<Setting> getSettings(Integer appId, Long owner) {
        return settingService.getSettings(appId, owner);
    }

    @Override
    public Setting getSetting(Integer appId, Long owner, String name) {
        return settingService.getSetting(appId, owner, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addSchedulingJob(SchedulingJob job) {
        schedulingJobService.save(job);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeSchedulingJob(Long jobId) {
        schedulingJobService.delete(new SchedulingJobDeleteParameter().setJobId(jobId));
    }

    @Override
    public RabbitPushResult sendRabbitDelayMsg(Object object, String routingKey, long delayTime, String boType, Long metaId, boolean idempotent){
        return delayedMessageProducer.send(object, routingKey, delayTime, boType, metaId, idempotent);
    }

    @Override
    public RabbitPushResult sendRabbitImmediateMsg(Object object, String routingKey, String boType, Long metaId, boolean idempotent){
        return immediateMessageProducer.send(object, routingKey, boType, metaId, idempotent);
    }

    @Override
    public RabbitLog getRabbitLogByKey(String key) {
        return rabbitLogService.getByKey(key);
    }

    @Override
    public boolean removeRabbitLog(Long metaId, String boType, String routingKey) {
        return rabbitLogService.remove(routingKey, metaId, boType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRabbitLog(RabbitLog rabbitLog) {
        rabbitLogService.updateByIdTE(rabbitLog);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveOrUpdateRabbitLog(RabbitLog rabbitLog) {
        rabbitLogService.saveOrUpdate(rabbitLog);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void finishRabbitLog(String key) {
        rabbitLogService.finish(key);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateFilesMetaId(List<UploadedFileDTO> files) {
        uploadedFileService.batchUpdateDTOByIdTE(files);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteFile(Long fileId) throws IOException {
        FileRemoveParameter parameter = new FileRemoveParameter();
        parameter.setFileIds(Arrays.asList(fileId));
        fileUploadService.removeFiles(parameter);
    }

    @Override
    public List<Label> getLabels(List<Long> labelIds, SFunction<Label, ?>... columns) {
        return labelService.getLabels(labelIds, Label::getLabelId, Label::getLabel);
    }

}
