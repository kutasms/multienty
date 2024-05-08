package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.chia.multienty.core.service.UploadedFileService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.base.UploadedFileDetailGetParameter;
import com.chia.multienty.core.parameter.base.UploadedFilePageGetParameter;
import com.chia.multienty.core.parameter.base.UploadedFileDeleteParameter;
import com.chia.multienty.core.parameter.base.UploadedFileSaveParameter;
import com.chia.multienty.core.parameter.base.UploadedFileUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 已上传文件前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/uploaded-file")
@RequiredArgsConstructor
@Api(tags = "已上传文件前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class UploadedFileController {
    private final UploadedFileService uploadedFileService;

    @PostMapping("/detail")
    @ApiOperation("获取已上传文件详情")
    public Result<UploadedFileDTO> getDetail(@RequestBody UploadedFileDetailGetParameter parameter) {
        UploadedFileDTO detail = uploadedFileService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取已上传文件分页列表")
    public Result<IPage<UploadedFileDTO>> getPage(@RequestBody UploadedFilePageGetParameter parameter) {
        IPage<UploadedFileDTO> page = uploadedFileService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新已上传文件")
    @WebLog(target = "UploadedFile")
    public Result<Boolean> update(@RequestBody UploadedFileUpdateParameter parameter) {
        uploadedFileService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存已上传文件")
    @WebLog(target = "UploadedFile")
    public Result<Boolean> save(@RequestBody UploadedFileSaveParameter parameter) {
        uploadedFileService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除已上传文件")
    @WebLog(target = "UploadedFile")
    public Result<Boolean> delete(@RequestBody UploadedFileDeleteParameter parameter) {
        uploadedFileService.delete(parameter);
        return new Result<>(true);
    }
}
