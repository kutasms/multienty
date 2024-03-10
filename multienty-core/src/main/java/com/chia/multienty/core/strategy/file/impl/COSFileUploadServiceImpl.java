package com.chia.multienty.core.strategy.file.impl;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.domain.enums.UploadFileType;
import com.chia.multienty.core.pojo.UploadedFile;
import com.chia.multienty.core.service.UploadedFileService;
import com.chia.multienty.core.strategy.file.FileUploadService;
import com.chia.multienty.core.parameter.base.FileRemoveParameter;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class COSFileUploadServiceImpl implements FileUploadService {
    private final static String YEAR_MONTH_SPLIT_BY_SLASH = "yyyy/MM";
    private final YamlMultientyProperties properties;
    private final UploadedFileService uploadedFileService;

    @Override
    public FileStorageMode getMode() {
        return FileStorageMode.COS;
    }

    @Override
    public String upload(MultipartFile file) throws Exception {

        //生成文件名
        String fileName = UUID.randomUUID().toString()
                .replace(SymbolEnum.HYPHEN.getCode(), SymbolEnum.EMPTY_STRING.getCode())
                + SymbolEnum.UNDER_LINE.getCode() + file.getOriginalFilename();

        //日期文件夹
        String relativePath = Paths.get("file", LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_SPLIT_BY_SLASH))).toString();
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        // web服务器存放的绝对路径
        String absolutePath = Paths.get(pathPrefix, relativePath).toString();

        java.io.File outFile = new java.io.File(Paths.get(absolutePath, fileName).toString());

        FileUtils.writeByteArrayToFile(outFile, file.getBytes());
        String uriPrefix = properties.getFile().getLocal().get("url-prefix");
        String url = new StringBuilder(uriPrefix)
                .append(relativePath)
                .append(SymbolEnum.SLASH.getCode())
                .append(fileName)
                .toString();
        //替换掉windows环境的\路径
        url = url.replaceAll(SymbolEnum.DOUBLE_BACK_SLASH.getCode(), SymbolEnum.SLASH.getCode())
                .replace(SymbolEnum.BACK_SLASH.getCode(), SymbolEnum.SLASH.getCode());
        return url;
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public UploadedFile upload2Bucket(MultipartFile file, String bucket, UploadFileType fileType) throws IOException {
        return upload2Bucket(file,bucket,fileType.getValue().byteValue());
    }

    @Override
    public UploadedFile upload2Bucket(MultipartFile file, String bucket, Byte fileTypeVal) throws IOException {
        //生成文件名
        String fileName = UUID.randomUUID().toString()
                .replace(SymbolEnum.HYPHEN.getCode(), SymbolEnum.EMPTY_STRING.getCode())
                + SymbolEnum.UNDER_LINE.getCode() + file.getOriginalFilename();
        String extension = Files.getFileExtension(fileName);
        //日期文件夹
        String relativePath = Paths.get("file", LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_SPLIT_BY_SLASH))).toString();
        // web服务器存放的绝对路径
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        String urlPrefix = properties.getFile().getLocal().get("url-prefix");
        String absolutePath = Paths.get(pathPrefix, bucket, relativePath).toString();

        String url = Paths.get(urlPrefix, bucket, relativePath, fileName).toString();
        //替换掉windows环境的\路径
        url = url.replaceAll(SymbolEnum.DOUBLE_BACK_SLASH.getCode(), SymbolEnum.SLASH.getCode())
                .replace(SymbolEnum.BACK_SLASH.getCode(), SymbolEnum.SLASH.getCode());

        String relPath = Paths.get(bucket, relativePath).toString();

        Map<String,String> map = new HashMap<>();
        map.put("url", url);
        map.put("path", relPath);
        map.put("extension", extension);
        map.put("fileName", fileName);
        UploadedFile uploadedFile = new UploadedFile()
                .setUrl(url)
                .setName(fileName)
                .setExtension(extension)
                .setOrgFileName(file.getOriginalFilename())
                .setPath(relPath)
                .setType(fileTypeVal);
        uploadedFile.setStatus(StatusEnum.NORMAL.getCode());
        uploadedFileService.save(uploadedFile);
        java.io.File outFile = new java.io.File(Paths.get(absolutePath, fileName).toString());
        FileUtils.writeByteArrayToFile(outFile, file.getBytes());
        return uploadedFile;
    }

    @Override
    public UploadedFile uploadStream(String fileName, byte[] bytes) throws Exception {
        //生成文件名
        String newFileName = UUID.randomUUID().toString()
                .replace(SymbolEnum.HYPHEN.getCode(), SymbolEnum.EMPTY_STRING.getCode()) + SymbolEnum.UNDER_LINE.getCode() + fileName;
        String extension = Files.getFileExtension(newFileName);
        //日期文件夹
        String relativePath = Paths.get("file", LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_SPLIT_BY_SLASH))).toString();
        String urlPrefix = properties.getFile().getLocal().get("url-prefix");
        // web服务器存放的绝对路径
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        String absolutePath = Paths.get(pathPrefix, relativePath).toString();

        String relPath = relativePath;

        java.io.File outFile = new java.io.File(Paths.get(absolutePath, newFileName).toString());
        FileUtils.writeByteArrayToFile(outFile, bytes);

        String url = Paths.get(urlPrefix, relativePath,newFileName).toString();

        //替换掉windows环境的\路径
        url.replaceAll(SymbolEnum.DOUBLE_BACK_SLASH.getCode(), SymbolEnum.SLASH.getCode())
                .replace(SymbolEnum.BACK_SLASH.getCode(), SymbolEnum.SLASH.getCode());

        UploadedFile uploadedFile = new UploadedFile()
                .setUrl(url)
                .setName(newFileName)
                .setExtension(extension)
                .setOrgFileName(fileName)
                .setPath(relPath)
                .setType(UploadFileType.UNKNOWN.getValue().byteValue());
        uploadedFile.setStatus(StatusEnum.NORMAL.getCode());
        uploadedFileService.save(uploadedFile);
        return uploadedFile;
    }

    @Override
    public InputStream getInputStream(String url) {
        return null;
    }

    @Override
    public Boolean removeFiles(FileRemoveParameter parameter) throws IOException {
        List<UploadedFile> files = uploadedFileService.listByIds(parameter.getFileIds());
        files.stream().forEach(f -> f.setStatus(StatusEnum.DELETED.getCode()));
        uploadedFileService.batchUpdateDTOByIdTE(files);
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        for (UploadedFile file : files) {
            // web服务器存放的绝对路径
            String fullFileName = Paths.get(pathPrefix, file.getPath(), file.getName()).toString();
            log.info("删除文件:{}", fullFileName);
            FileUtils.forceDelete(new File(fullFileName));
        }
        return true;
    }
}
