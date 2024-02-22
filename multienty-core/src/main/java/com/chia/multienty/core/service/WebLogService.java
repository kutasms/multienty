package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.WebLogDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.WebLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.log.WebLogDetailGetParameter;
import com.chia.multienty.core.parameter.log.WebLogPageGetParameter;
import com.chia.multienty.core.parameter.log.WebLogDeleteParameter;
import com.chia.multienty.core.parameter.log.WebLogSaveParameter;
import com.chia.multienty.core.parameter.log.WebLogUpdateParameter;

/**
 * <p>
 * web请求记录 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
public interface WebLogService extends KutaBaseService<WebLog> {


    WebLogDTO getDetail(WebLogDetailGetParameter parameter);

    void delete(WebLogDeleteParameter parameter);

    IPage<WebLogDTO> getPage(WebLogPageGetParameter parameter);

    void save(WebLogSaveParameter parameter);

    void update(WebLogUpdateParameter parameter);


}
