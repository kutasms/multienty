package com.chia.multienty.core.strategy.file.impl;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.enums.UploadFileType;
import com.chia.multienty.core.pojo.UploadedFile;
import com.chia.multienty.core.parameter.base.FileRemoveParameter;
import com.chia.multienty.core.strategy.file.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class OBSFileUploadServiceImpl implements FileUploadService {
    @Override
    public FileStorageMode getMode() {
        return FileStorageMode.OBS;
    }

    @Override
    public String upload(MultipartFile file) throws Exception {
        return null;
    }

    @Override
    public UploadedFile upload2Bucket(MultipartFile file, String bucket, UploadFileType fileType) throws IOException {
        return null;
    }

    @Override
    public UploadedFile upload2Bucket(MultipartFile file, String bucket, Byte fileTypeVal) throws IOException {
        return null;
    }

    @Override
    public UploadedFile uploadStream(String fileName, byte[] bytes) throws Exception {
        return null;
    }

    @Override
    public InputStream getInputStream(String url) {
        return null;
    }

    @Override
    public Boolean removeFiles(FileRemoveParameter parameter) throws IOException {
        return null;
    }
}
