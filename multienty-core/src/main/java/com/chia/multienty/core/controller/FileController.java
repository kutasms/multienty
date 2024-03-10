package com.chia.multienty.core.controller;

import com.chia.multienty.core.annotation.MultiWebLog;
import com.chia.multienty.core.domain.basic.KeyValuePair;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.basic.UploadResult;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.UploadFileType;
import com.chia.multienty.core.parameter.base.FileRemoveParameter;
import com.chia.multienty.core.pojo.UploadedFile;
import com.chia.multienty.core.strategy.file.FileUploadService;
import com.chia.multienty.core.tools.MultientyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 文件前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Api(tags = "文件前端控制器")
public class FileController {


    @GetMapping("/upload")
    @ApiOperation("上传文件")
    public Result<String> upload(MultipartFile file) throws Exception {
        Result result=new Result();
        if(null != file){
            if(file.getOriginalFilename().contains("mp4")){
                //如果上传的视频,校验大小不能超过2M
                if(file.getSize()>1024*1024*50){
                    result= new Result(HttpResultEnum.SYSTEM_MP4_FILE_MAXIMUM_50M);
                    return result;
                }
            }
            String[] allowedImageSuffixArray = new String[] {"jpg","png"};
            Optional<String> isImage = Arrays.stream(allowedImageSuffixArray).filter(p -> p.equals(file.getOriginalFilename())).findAny();
            if(isImage.isPresent()) {
                if(file.getSize()>1024*1024*2){
                    result= new Result(HttpResultEnum.SYSTEM_IMG_FILE_MAXIMUM_2M);
                    return result;
                }
            }
            FileUploadService service = MultientyContext.getResourceMappingAlgorithm().getFileUploadService();
            String url = service.upload(file);
            result=new Result(url,HttpResultEnum.SUCCESS);
        }
        return result;
    }

    @GetMapping("/upload2Bucket")
    @ApiOperation("上传文件")
    @SneakyThrows
    public Result<UploadedFile> upload2Bucket(MultipartFile file, HttpServletRequest request) {
        Result<UploadedFile> result = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] buckets = parameterMap.get("bucket");
        String[] types = parameterMap.get("type");
        UploadFileType fileType = types == null || types.length == 0
                ? UploadFileType.UNKNOWN
                : UploadFileType.parse(Integer.parseInt(types[0]));
        if(null != file){
            if(file.getOriginalFilename().contains("mp4")){
                //如果上传的视频,校验大小不能超过2M
                if(file.getSize()>1024*1024*50){
                    result= new Result(HttpResultEnum.SYSTEM_MP4_FILE_MAXIMUM_50M);
                    return result;
                }
            }
            String[] allowedImageSuffixArray = new String[] {"jpg","png","jpeg", "gif"};
            Optional<String> isImage = Arrays.stream(allowedImageSuffixArray).filter(p -> p.equals(file.getOriginalFilename())).findAny();
            if(isImage.isPresent()) {
                if(file.getSize()>1024*1024*2){
                    result= new Result(HttpResultEnum.SYSTEM_IMG_FILE_MAXIMUM_2M);
                    return result;
                }
            }
            FileUploadService service = MultientyContext.getResourceMappingAlgorithm().getFileUploadService();
            UploadedFile uploadedFile = service.upload2Bucket(file, buckets[0], fileType);
            UploadResult uploadResult = new UploadResult();
            uploadResult.setUrl(uploadedFile.getUrl());
            uploadResult.setUploadedFile(uploadedFile);
            uploadResult.setParams(parameterMap);
            result=new Result(uploadResult,HttpResultEnum.SUCCESS);
        }
        return result;
    }

    @PostMapping("getUploadFileTypes")
    @ApiOperation(value = "获取上传文件类型")
    public Result<List<KeyValuePair>> getUploadFileTypes() {
        List<KeyValuePair> list = new ArrayList<>();
        for (UploadFileType fileType : UploadFileType.values()) {
            if(fileType.equals(UploadFileType.UNKNOWN)) {
                continue;
            }
            list.add(new KeyValuePair().setKey(fileType.getDescribe()).setValue(fileType.getValue()));
        }
        return new Result<>(list);
    }

    @PostMapping("removeFiles")
    @ApiOperation(value = "删除多个文件")
    @MultiWebLog
    public Result<Boolean> removeFiles(@RequestBody FileRemoveParameter parameter) throws IOException {
        FileUploadService service = MultientyContext.getResourceMappingAlgorithm().getFileUploadService();
        service.removeFiles(parameter);
        return new Result<>(true);
    }
}
