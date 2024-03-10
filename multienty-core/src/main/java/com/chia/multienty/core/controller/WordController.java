package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WordDTO;
import com.chia.multienty.core.service.WordService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.base.WordDetailGetParameter;
import com.chia.multienty.core.parameter.base.WordPageGetParameter;
import com.chia.multienty.core.parameter.base.WordDeleteParameter;
import com.chia.multienty.core.parameter.base.WordSaveParameter;
import com.chia.multienty.core.parameter.base.WordUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 关键词前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
@Api(tags = "关键词前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class WordController {
    private final WordService wordService;

    @PostMapping("/detail")
    @ApiOperation("获取关键词信息详情")
    public Result<WordDTO> getDetail(@RequestBody WordDetailGetParameter parameter) {
        WordDTO detail = wordService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取关键词信息分页列表")
    public Result<IPage<WordDTO>> getPage(@RequestBody WordPageGetParameter parameter) {
        IPage<WordDTO> page = wordService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新关键词信息")
    @WebLog
    public Result<Boolean> update(@RequestBody WordUpdateParameter parameter) {
        wordService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存关键词信息")
    @WebLog
    public Result<Boolean> save(@RequestBody WordSaveParameter parameter) {
        wordService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除关键词信息")
    @WebLog
    public Result<Boolean> delete(@RequestBody WordDeleteParameter parameter) {
        wordService.delete(parameter);
        return new Result<>(true);
    }
}
