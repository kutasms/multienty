package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatOfficialAccountDTO;
import com.chia.multienty.core.service.WechatOfficialAccountService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 微信公众号账户 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@RestController
@RequestMapping("/wechat-official-account")
@RequiredArgsConstructor
@Api(tags = "微信公众号账户")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class WechatOfficialAccountController {
    private final WechatOfficialAccountService wechatOfficialAccountService;

    @PostMapping("/detail")
    @ApiOperation("获取微信公众号账户详情")
    public Result<WechatOfficialAccountDTO> getDetail(@RequestBody WechatOfficialAccountDetailGetParameter parameter) {
        WechatOfficialAccountDTO detail = wechatOfficialAccountService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信公众号账户分页列表")
    public Result<IPage<WechatOfficialAccountDTO>> getPage(@RequestBody WechatOfficialAccountPageGetParameter parameter) {
        IPage<WechatOfficialAccountDTO> page = wechatOfficialAccountService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信公众号账户")
    @WebLog(target = "WechatOfficialAccount")
    public Result<Boolean> update(@RequestBody WechatOfficialAccountUpdateParameter parameter) {
        wechatOfficialAccountService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信公众号账户")
    @WebLog(target = "WechatOfficialAccount")
    public Result<Boolean> save(@RequestBody WechatOfficialAccountSaveParameter parameter) {
        wechatOfficialAccountService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信公众号账户")
    @WebLog(target = "WechatOfficialAccount")
    public Result<Boolean> enable(@RequestBody WechatOfficialAccountEnableParameter parameter) {
        wechatOfficialAccountService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信公众号账户")
    @WebLog(target = "WechatOfficialAccount")
    public Result<Boolean> save(@RequestBody WechatOfficialAccountDisableParameter parameter) {
        wechatOfficialAccountService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信公众号账户")
    @WebLog(target = "WechatOfficialAccount")
    public Result<Boolean> delete(@RequestBody WechatOfficialAccountDeleteParameter parameter) {
        wechatOfficialAccountService.delete(parameter);
        return new Result<>(true);
    }

}
