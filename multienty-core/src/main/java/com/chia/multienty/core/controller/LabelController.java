package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.LabelDTO;
import com.chia.multienty.core.service.LabelService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.base.LabelDetailGetParameter;
import com.chia.multienty.core.parameter.base.LabelPageGetParameter;
import com.chia.multienty.core.parameter.base.LabelDeleteParameter;
import com.chia.multienty.core.parameter.base.LabelSaveParameter;
import com.chia.multienty.core.parameter.base.LabelUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 标签前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/label")
@RequiredArgsConstructor
@Api(tags = "标签前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class LabelController {
    private final LabelService labelService;

    @PostMapping("/detail")
    @ApiOperation("获取标签详情")
    public Result<LabelDTO> getDetail(@RequestBody LabelDetailGetParameter parameter) {
        LabelDTO detail = labelService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取标签分页列表")
    public Result<IPage<LabelDTO>> getPage(@RequestBody LabelPageGetParameter parameter) {
        IPage<LabelDTO> page = labelService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新标签")
    @WebLog(target = "Label")
    public Result<Boolean> update(@RequestBody LabelUpdateParameter parameter) {
        labelService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存标签")
    @WebLog(target = "Label")
    public Result<Boolean> save(@RequestBody LabelSaveParameter parameter) {
        labelService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除标签")
    @WebLog(target = "Label")
    public Result<Boolean> delete(@RequestBody LabelDeleteParameter parameter) {
        labelService.delete(parameter);
        return new Result<>(true);
    }
}
