package com.chia.multienty.core.service;

import com.chia.multienty.core.pojo.SchedulingJob;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.SchedulingJobDTO;
import com.chia.multienty.core.parameter.base.SchedulingJobDetailGetParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobPageGetParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobDeleteParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobEnableParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobDisableParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobSaveParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobUpdateParameter;
/**
 * <p>
 * 调度任务 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
public interface SchedulingJobService extends KutaBaseService<SchedulingJob> {

    SchedulingJobDTO getDetail(SchedulingJobDetailGetParameter parameter);

    void delete(SchedulingJobDeleteParameter parameter);

    IPage<SchedulingJobDTO> getPage(SchedulingJobPageGetParameter parameter);
    void enable(SchedulingJobEnableParameter parameter);

    void disable(SchedulingJobDisableParameter parameter);

    void save(SchedulingJobSaveParameter parameter);

    void update(SchedulingJobUpdateParameter parameter);

}
