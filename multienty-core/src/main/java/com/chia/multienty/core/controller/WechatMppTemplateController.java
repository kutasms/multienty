package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatMppTemplateDTO;
import com.chia.multienty.core.service.WechatMppTemplateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplatePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 微信小程序模版 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@RestController
@RequestMapping("/wechat-mpp-template")
@RequiredArgsConstructor
@Api(tags = "微信小程序模版")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatMppTemplateController {
    private final WechatMppTemplateService wechatMppTemplateService;

    @PostMapping("/detail")
    @ApiOperation("获取微信小程序模版详情")
    public Result<WechatMppTemplateDTO> getDetail(@RequestBody WechatMppTemplateDetailGetParameter parameter) {
        WechatMppTemplateDTO detail = wechatMppTemplateService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信小程序模版分页列表")
    public Result<IPage<WechatMppTemplateDTO>> getPage(@RequestBody WechatMppTemplatePageGetParameter parameter) {
        IPage<WechatMppTemplateDTO> page = wechatMppTemplateService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信小程序模版")
    @WebLog(target = "WechatMppTemplate")
    public Result<Boolean> update(@RequestBody WechatMppTemplateUpdateParameter parameter) {
        wechatMppTemplateService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信小程序模版")
    @WebLog(target = "WechatMppTemplate")
    public Result<Boolean> save(@RequestBody WechatMppTemplateSaveParameter parameter) {
        wechatMppTemplateService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信小程序模版")
    @WebLog(target = "WechatMppTemplate")
    public Result<Boolean> enable(@RequestBody WechatMppTemplateEnableParameter parameter) {
        wechatMppTemplateService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信小程序模版")
    @WebLog(target = "WechatMppTemplate")
    public Result<Boolean> save(@RequestBody WechatMppTemplateDisableParameter parameter) {
        wechatMppTemplateService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信小程序模版")
    @WebLog(target = "WechatMppTemplate")
    public Result<Boolean> delete(@RequestBody WechatMppTemplateDeleteParameter parameter) {
        wechatMppTemplateService.delete(parameter);
        return new Result<>(true);
    }

}
