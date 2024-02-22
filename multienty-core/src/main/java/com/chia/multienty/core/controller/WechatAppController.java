package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.service.WechatAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 微信应用 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@RestController
@RequestMapping("/wechat-app")
@RequiredArgsConstructor
@Api(tags = "微信应用")
public class WechatAppController {
    private final WechatAppService wechatAppService;

    @PostMapping("/detail")
    @ApiOperation("获取微信应用详情")
    public Result<WechatAppDTO> getDetail(@RequestBody WechatAppDetailGetParameter parameter) {
        WechatAppDTO detail = wechatAppService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信应用分页列表")
    public Result<IPage<WechatAppDTO>> getPage(@RequestBody WechatAppPageGetParameter parameter) {
        IPage<WechatAppDTO> page = wechatAppService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信应用")
    @WebLog
    public Result<Boolean> update(@RequestBody WechatAppUpdateParameter parameter) {
        wechatAppService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信应用")
    @WebLog
    public Result<Boolean> save(@RequestBody WechatAppSaveParameter parameter) {
        wechatAppService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信应用")
    @WebLog
    public Result<Boolean> enable(@RequestBody WechatAppEnableParameter parameter) {
        wechatAppService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信应用")
    @WebLog
    public Result<Boolean> save(@RequestBody WechatAppDisableParameter parameter) {
        wechatAppService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信应用")
    @WebLog
    public Result<Boolean> delete(@RequestBody WechatAppDeleteParameter parameter) {
        wechatAppService.delete(parameter);
        return new Result<>(true);
    }

}
