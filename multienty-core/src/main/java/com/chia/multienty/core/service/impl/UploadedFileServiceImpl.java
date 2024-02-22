package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.chia.multienty.core.mapper.UploadedFileMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.UploadedFile;
import com.chia.multienty.core.service.UploadedFileService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.base.UploadedFileDetailGetParameter;
import com.chia.multienty.core.parameter.base.UploadedFilePageGetParameter;
import com.chia.multienty.core.parameter.base.UploadedFileDeleteParameter;
import com.chia.multienty.core.parameter.base.UploadedFileSaveParameter;
import com.chia.multienty.core.parameter.base.UploadedFileUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 存储文件 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */
@Service
public class UploadedFileServiceImpl extends KutaBaseServiceImpl<UploadedFileMapper, UploadedFile> implements UploadedFileService {


    @Override
    public UploadedFileDTO getDetail(UploadedFileDetailGetParameter parameter) {
        return selectJoinOne(UploadedFileDTO.class,
                        MPJWrappers.<UploadedFile>lambdaJoin().eq(UploadedFile::getFileId, parameter.getFileId()));
    }

    @Override
    public void delete(UploadedFileDeleteParameter parameter) {
        removeByIdTE(parameter.getFileId());
    }

    @Override
    public IPage<UploadedFileDTO> getPage(UploadedFilePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), UploadedFileDTO.class,
                new KutaLambdaWrapper<UploadedFile>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getFileIds()), UploadedFile::getFileId, parameter.getFileIds())
        );
    }

    @Override
    public void save(UploadedFileSaveParameter parameter) {
        UploadedFile uploadedFile = new UploadedFile();
        BeanUtils.copyProperties(parameter, uploadedFile);
        saveTE(uploadedFile);
        parameter.setFileId(uploadedFile.getFileId());
    }

    @Override
    public void update(UploadedFileUpdateParameter parameter) {
        UploadedFile uploadedFile = new UploadedFile();
        BeanUtils.copyProperties(parameter, uploadedFile);
        updateByIdTE(uploadedFile);
    }
}
