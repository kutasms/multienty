package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.JsonConfigDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.JsonConfig;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.JsonConfigDetailGetParameter;
import com.chia.multienty.core.parameter.base.JsonConfigPageGetParameter;
import com.chia.multienty.core.parameter.base.JsonConfigDeleteParameter;
import com.chia.multienty.core.parameter.base.JsonConfigSaveParameter;
import com.chia.multienty.core.parameter.base.JsonConfigUpdateParameter;

/**
 * <p>
 * Json格式配置信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface JsonConfigService extends KutaBaseService<JsonConfig> {

    JsonConfigDTO getDetail(JsonConfigDetailGetParameter parameter);

    void delete(JsonConfigDeleteParameter parameter);


    IPage<JsonConfigDTO> getPage(JsonConfigPageGetParameter parameter);

    void save(JsonConfigSaveParameter parameter);

    void update(JsonConfigUpdateParameter parameter);


}
