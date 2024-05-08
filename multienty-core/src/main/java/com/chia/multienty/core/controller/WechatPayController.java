package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatPayDTO;
import com.chia.multienty.core.service.WechatPayService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatPayDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatPaySaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 微信支付 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@RestController
@RequestMapping("/wechat-pay")
@RequiredArgsConstructor
@Api(tags = "微信支付")
public class WechatPayController {
    private final WechatPayService wechatPayService;

    @PostMapping("/detail")
    @ApiOperation("获取微信支付详情")
    public Result<WechatPayDTO> getDetail(@RequestBody WechatPayDetailGetParameter parameter) {
        WechatPayDTO detail = wechatPayService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信支付分页列表")
    public Result<IPage<WechatPayDTO>> getPage(@RequestBody WechatPayPageGetParameter parameter) {
        IPage<WechatPayDTO> page = wechatPayService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信支付")
    @WebLog(target = "WechatPay")
    public Result<Boolean> update(@RequestBody WechatPayUpdateParameter parameter) {
        wechatPayService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信支付")
    @WebLog(target = "WechatPay")
    public Result<Boolean> save(@RequestBody WechatPaySaveParameter parameter) {
        wechatPayService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信支付")
    @WebLog(target = "WechatPay")
    public Result<Boolean> enable(@RequestBody WechatPayEnableParameter parameter) {
        wechatPayService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信支付")
    @WebLog(target = "WechatPay")
    public Result<Boolean> disable(@RequestBody WechatPayDisableParameter parameter) {
        wechatPayService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信支付")
    @WebLog(target = "WechatPay")
    public Result<Boolean> delete(@RequestBody WechatPayDeleteParameter parameter) {
        wechatPayService.delete(parameter);
        return new Result<>(true);
    }

}
