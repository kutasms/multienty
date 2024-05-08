package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.ChineseCityDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.LabelValuePair;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.service.ChineseCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 城市前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/chinese-city")
@Api(tags = "城市前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class ChineseCityController {
    @Autowired
    private ChineseCityService chineseCityService;
    @PostMapping("list")
    @ApiOperation(value = "获取城市列表")
    public Result<List<LabelValuePair>> getCityList(@RequestBody CityListGetParameter parameter) {
        List<LabelValuePair> list = chineseCityService.getList(parameter);
        return new Result<>(list);
    }

    @PostMapping("full")
    @ApiOperation(value = "获取城市信息")
    public Result<ChineseCityDTO> getFullCity(@RequestBody CityGetParameter parameter) throws KutaRuntimeException {
        ChineseCityDTO city = chineseCityService.getFull(parameter);
        return new Result<>(city, HttpResultEnum.SUCCESS);
    }

    @PostMapping("/detail")
    @ApiOperation("获取城市详情")
    public Result<ChineseCityDTO> getDetail(@RequestBody ChineseCityDetailGetParameter parameter) {
        ChineseCityDTO detail = chineseCityService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取城市分页列表")
    public Result<IPage<ChineseCityDTO>> getPage(@RequestBody ChineseCityPageGetParameter parameter) {
        IPage<ChineseCityDTO> page = chineseCityService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新城市信息")
    @WebLog
    public Result<Boolean> update(@RequestBody ChineseCityUpdateParameter parameter) {
        chineseCityService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存城市信息")
    @WebLog
    public Result<Boolean> save(@RequestBody ChineseCitySaveParameter parameter) {
        chineseCityService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除城市信息")
    @WebLog
    public Result<Boolean> delete(@RequestBody ChineseCityDeleteParameter parameter) {
        chineseCityService.delete(parameter);
        return new Result<>(true);
    }
}
