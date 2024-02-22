package com.chia.multienty.core.strategy.file;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.enums.UploadFileType;
import com.chia.multienty.core.pojo.UploadedFile;
import com.chia.multienty.core.parameter.base.FileRemoveParameter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件策略接口
 * @author multi tenant
 */
public interface FileUploadService {

    FileStorageMode getMode();

    String upload(MultipartFile file) throws Exception;

    UploadedFile upload2Bucket(MultipartFile file, String bucket, UploadFileType fileType) throws IOException;

    UploadedFile upload2Bucket(MultipartFile file, String bucket, Byte fileTypeVal) throws IOException;

    UploadedFile uploadStream(String fileName, byte[] bytes) throws Exception;

    InputStream getInputStream(String url);

    Boolean removeFiles(FileRemoveParameter parameter) throws IOException;
}
