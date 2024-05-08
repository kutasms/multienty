package com.chia.multienty.core.strategy.file.impl;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.domain.enums.UploadFileType;
import com.chia.multienty.core.parameter.base.FileRemoveParameter;
import com.chia.multienty.core.pojo.UploadedFile;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.UploadedFileService;
import com.chia.multienty.core.strategy.file.FileUploadService;
import com.google.common.io.Files;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class LocalFileUploadServiceImpl implements FileUploadService {

    @Override
    public FileStorageMode getMode() {
        return FileStorageMode.LOCAL;
    }

    private final static String YEAR_MONTH_SPLIT_BY_SLASH = "yyyy/MM";

    @Autowired
    private YamlMultientyProperties properties;
    @Autowired
    private UploadedFileService uploadedFileService;


    private String replaceAll(String target) {
        return target.replaceAll(Matcher.quoteReplacement(File.separator), SymbolEnum.SLASH.getCode());
    }

    @Override
    public String upload(MultipartFile file) throws Exception {
        String uuid = UUID.randomUUID().toString();
        //生成文件名
        final String fileName = uuid
                .replace(SymbolEnum.HYPHEN.getCode(), SymbolEnum.EMPTY_STRING.getCode())
                + SymbolEnum.UNDER_LINE.getCode() + file.getOriginalFilename();

        //日期文件夹
        String relativePath = Paths.get("file", LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_SPLIT_BY_SLASH))).toString();
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        // web服务器存放的绝对路径
        String absolutePath = Paths.get(pathPrefix, relativePath).toString();

        File pathFile = new File(absolutePath);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }

        java.io.File outFile = new java.io.File(Paths.get(absolutePath, fileName).toString());
        FileUtils.writeByteArrayToFile(outFile, file.getBytes());
        String uriPrefix = properties.getFile().getLocal().get("url-prefix");
        String url = new StringBuilder(uriPrefix)
                .append(replaceAll(relativePath))
                .append(SymbolEnum.SLASH.getCode())
                .append(fileName)
                .toString();
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
        String uuid = UUID.randomUUID().toString();
        String extension = Files.getFileExtension(file.getOriginalFilename());
        //生成文件名
        final String fileName = uuid
                .replace(SymbolEnum.HYPHEN.getCode(), SymbolEnum.EMPTY_STRING.getCode()) + SymbolEnum.DOT.getCode() + extension;

        //日期文件夹
        String relativePath = Paths.get(LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_SPLIT_BY_SLASH))).toString();
        // web服务器存放的绝对路径
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        String urlPrefix = properties.getFile().getLocal().get("url-prefix");
        String absolutePath = Paths.get(pathPrefix, bucket, relativePath).toString();

        String url = urlPrefix + SymbolEnum.SLASH.getCode() + bucket + SymbolEnum.SLASH.getCode() +
                replaceAll(relativePath) + SymbolEnum.SLASH.getCode() + fileName;

        String finalFileName = Paths.get(absolutePath, fileName).toString();

        UploadedFile uploadedFile = new UploadedFile()
                .setUrl(url)
                .setName(fileName)
                .setExtension(extension)
                .setOrgFileName(file.getOriginalFilename())
                .setPath(Paths.get(bucket, relativePath, fileName).toString())
                .setType(fileTypeVal);
        uploadedFile.setStatus(StatusEnum.NORMAL.getCode());
        uploadedFileService.save(uploadedFile);

        File pathFile = new File(absolutePath);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        java.io.File outFile = new java.io.File(finalFileName);
        FileUtils.writeByteArrayToFile(outFile, file.getBytes());
        return uploadedFile;
    }

    @Override
    public UploadedFile uploadStream(final String fileName, byte[] bytes) throws Exception {
        String uuid = UUID.randomUUID().toString();
        //生成文件名
        String newFileName = uuid
                .replace(SymbolEnum.HYPHEN.getCode(), SymbolEnum.EMPTY_STRING.getCode()) + SymbolEnum.UNDER_LINE.getCode() + fileName;
        String extension = Files.getFileExtension(newFileName);
        //日期文件夹
        String relativePath = Paths.get("file", LocalDate.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_SPLIT_BY_SLASH))).toString();
        String urlPrefix = properties.getFile().getLocal().get("url-prefix");
        // web服务器存放的绝对路径
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        String absolutePath = Paths.get(pathPrefix, relativePath).toString();

        String relPath = relativePath;
        String url = urlPrefix + SymbolEnum.SLASH.getCode() +
                replaceAll(relativePath) + SymbolEnum.SLASH.getCode() + fileName;

        UploadedFile uploadedFile = new UploadedFile()
                .setUrl(url)
                .setName(newFileName)
                .setExtension(extension)
                .setOrgFileName(fileName)
                .setPath(relPath)
                .setType(UploadFileType.UNKNOWN.getValue().byteValue());
        uploadedFile.setStatus(StatusEnum.NORMAL.getCode());
        uploadedFileService.save(uploadedFile);

        File pathFile = new File(absolutePath);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        java.io.File outFile = new java.io.File(Paths.get(absolutePath, newFileName).toString());
        FileUtils.writeByteArrayToFile(outFile, bytes);
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
        uploadedFileService.batchRemoveByIdTE(files.stream().map(m->m.getFileId()).collect(Collectors.toList()));
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        for (UploadedFile file : files) {
            // web服务器存放的绝对路径
            String fullFileName = Paths.get(pathPrefix, file.getPath(), file.getName()).toString();
            log.info("删除文件:{}", fullFileName);
            File actualFile = new File(fullFileName);
            if(actualFile.exists()) {
                FileUtils.forceDelete(actualFile);
            }
        }
        return true;
    }
}
