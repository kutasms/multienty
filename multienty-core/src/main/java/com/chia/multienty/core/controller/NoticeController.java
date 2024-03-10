package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.NoticeDTO;
import com.chia.multienty.core.service.NoticeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.base.NoticeDetailGetParameter;
import com.chia.multienty.core.parameter.base.NoticePageGetParameter;
import com.chia.multienty.core.parameter.base.NoticeDeleteParameter;
import com.chia.multienty.core.parameter.base.NoticeSaveParameter;
import com.chia.multienty.core.parameter.base.NoticeUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 通知前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Api(tags = "通知前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/detail")
    @ApiOperation("获取通知详情")
    public Result<NoticeDTO> getDetail(@RequestBody NoticeDetailGetParameter parameter) {
        NoticeDTO detail = noticeService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取通知分页列表")
    public Result<IPage<NoticeDTO>> getPage(@RequestBody NoticePageGetParameter parameter) {
        IPage<NoticeDTO> page = noticeService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新通知")
    @WebLog
    public Result<Boolean> update(@RequestBody NoticeUpdateParameter parameter) {
        noticeService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存通知")
    @WebLog
    public Result<Boolean> save(@RequestBody NoticeSaveParameter parameter) {
        noticeService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除通知")
    @WebLog
    public Result<Boolean> delete(@RequestBody NoticeDeleteParameter parameter) {
        noticeService.delete(parameter);
        return new Result<>(true);
    }
}
