package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.UploadedFile;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.UploadedFileDetailGetParameter;
import com.chia.multienty.core.parameter.base.UploadedFilePageGetParameter;
import com.chia.multienty.core.parameter.base.UploadedFileDeleteParameter;
import com.chia.multienty.core.parameter.base.UploadedFileSaveParameter;
import com.chia.multienty.core.parameter.base.UploadedFileUpdateParameter;

/**
 * <p>
 * 存储文件 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
public interface UploadedFileService extends KutaBaseService<UploadedFile> {

    UploadedFileDTO getDetail(UploadedFileDetailGetParameter parameter);

    IPage<UploadedFileDTO> getPage(UploadedFilePageGetParameter parameter);

    void save(UploadedFileSaveParameter parameter);

    void update(UploadedFileUpdateParameter parameter);

    void delete(UploadedFileDeleteParameter parameter);
}
