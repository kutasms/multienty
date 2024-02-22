package com.chia.multienty.core.controller;


import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WebLogDTO;
import com.chia.multienty.core.parameter.log.WebLogDetailGetParameter;
import com.chia.multienty.core.parameter.log.WebLogPageGetParameter;
import com.chia.multienty.core.service.WebLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * web请求记录前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/web-log")
@RequiredArgsConstructor
@Api(tags = "web请求记录前端控制器")
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant", name = "base-module-enabled", havingValue = "true")
public class WebLogController {
    private final WebLogService webLogService;

    @PostMapping("/detail")
    @ApiOperation("获取web请求记录详情")
    public Result<WebLogDTO> getDetail(@RequestBody WebLogDetailGetParameter parameter) {
        WebLogDTO detail = webLogService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取web请求记录分页列表")
    public Result<IPage<WebLogDTO>> getPage(@RequestBody WebLogPageGetParameter parameter) {
        IPage<WebLogDTO> page = webLogService.getPage(parameter);
        return new Result<>(page);
    }

//    @PostMapping("/update")
//    @ApiOperation("更新web请求记录")
//    @WebLog
//    public Result<Boolean> update(@RequestBody WebLogUpdateParameter parameter) {
//        webLogService.update(parameter);
//        return new Result<>(true);
//    }

//    @PostMapping("/save")
//    @ApiOperation("保存web请求记录")
//    @WebLog
//    public Result<Boolean> save(@RequestBody WebLogSaveParameter parameter) {
//        webLogService.save(parameter);
//        return new Result<>(true);
//    }

//    @DeleteMapping("/delete")
//    @ApiOperation("删除web请求记录")
//    @WebLog
//    public Result<Boolean> delete(@RequestBody WebLogDeleteParameter parameter) {
//        webLogService.delete(parameter);
//        return new Result<>(true);
//    }
}
