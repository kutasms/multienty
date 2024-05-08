package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatBetaMppRegisterAuditDTO;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.service.WechatBetaMppRegisterAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 微信试用小程序注册审核单 服务
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@RestController
@Validated
@RequestMapping("/wechat-beta-mpp-register-audit")
@RequiredArgsConstructor
@Api(tags = "微信试用小程序注册审核单")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatBetaMppRegisterAuditController {

    private final WechatBetaMppRegisterAuditService wechatBetaMppRegisterAuditService;

    @PostMapping("/detail")
    @ApiOperation("获取微信试用小程序注册审核单详情")
    public Result<WechatBetaMppRegisterAuditDTO> getDetail(@Validated @RequestBody WechatBetaMppRegisterAuditDetailGetParameter parameter) {
        WechatBetaMppRegisterAuditDTO detail = wechatBetaMppRegisterAuditService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信试用小程序注册审核单分页列表")
    public Result<IPage<WechatBetaMppRegisterAuditDTO>> getPage(@Validated @RequestBody WechatBetaMppRegisterAuditPageGetParameter parameter) {
        IPage<WechatBetaMppRegisterAuditDTO> page = wechatBetaMppRegisterAuditService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信试用小程序注册审核单")
    @WebLog(target = "WechatBetaMppRegisterAudit")
    public Result<Boolean> update(@Validated @RequestBody WechatBetaMppRegisterAuditUpdateParameter parameter) {
        wechatBetaMppRegisterAuditService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信试用小程序注册审核单")
    @WebLog(target = "WechatBetaMppRegisterAudit")
    public Result<Boolean> save(@Validated @RequestBody WechatBetaMppRegisterAuditSaveParameter parameter) {
        wechatBetaMppRegisterAuditService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信试用小程序注册审核单")
    @WebLog(target = "WechatBetaMppRegisterAudit")
    public Result<Boolean> enable(@Validated @RequestBody WechatBetaMppRegisterAuditEnableParameter parameter) {
        wechatBetaMppRegisterAuditService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信试用小程序注册审核单")
    @WebLog(target = "WechatBetaMppRegisterAudit")
    public Result<Boolean> disable(@Validated @RequestBody WechatBetaMppRegisterAuditDisableParameter parameter) {
        wechatBetaMppRegisterAuditService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信试用小程序注册审核单")
    @WebLog(target = "WechatBetaMppRegisterAudit")
    public Result<Boolean> delete(@Validated @RequestBody WechatBetaMppRegisterAuditDeleteParameter parameter) {
        wechatBetaMppRegisterAuditService.delete(parameter);
        return new Result<>(true);
    }

}
