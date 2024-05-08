package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatBetaMppAuthAuditDTO;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.service.WechatBetaMppAuthAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 微信试用小程序转正审核单 服务
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@RestController
@Validated
@RequestMapping("/wechat-beta-mpp-auth-audit")
@RequiredArgsConstructor
@Api(tags = "微信试用小程序转正审核单")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatBetaMppAuthAuditController {

    private final WechatBetaMppAuthAuditService wechatBetaMppAuthAuditService;

    @PostMapping("/detail")
    @ApiOperation("获取微信试用小程序转正审核单详情")
    public Result<WechatBetaMppAuthAuditDTO> getDetail(@Validated @RequestBody WechatBetaMppAuthAuditDetailGetParameter parameter) {
        WechatBetaMppAuthAuditDTO detail = wechatBetaMppAuthAuditService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信试用小程序转正审核单分页列表")
    public Result<IPage<WechatBetaMppAuthAuditDTO>> getPage(@Validated @RequestBody WechatBetaMppAuthAuditPageGetParameter parameter) {
        IPage<WechatBetaMppAuthAuditDTO> page = wechatBetaMppAuthAuditService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信试用小程序转正审核单")
    @WebLog(target = "WechatBetaMppAuthAudit")
    public Result<Boolean> update(@Validated @RequestBody WechatBetaMppAuthAuditUpdateParameter parameter) {
        wechatBetaMppAuthAuditService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信试用小程序转正审核单")
    @WebLog(target = "WechatBetaMppAuthAudit")
    public Result<Boolean> save(@Validated @RequestBody WechatBetaMppAuthAuditSaveParameter parameter) {
        wechatBetaMppAuthAuditService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信试用小程序转正审核单")
    @WebLog(target = "WechatBetaMppAuthAudit")
    public Result<Boolean> enable(@Validated @RequestBody WechatBetaMppAuthAuditEnableParameter parameter) {
        wechatBetaMppAuthAuditService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信试用小程序转正审核单")
    @WebLog(target = "WechatBetaMppAuthAudit")
    public Result<Boolean> disable(@Validated @RequestBody WechatBetaMppAuthAuditDisableParameter parameter) {
        wechatBetaMppAuthAuditService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信试用小程序转正审核单")
    @WebLog(target = "WechatBetaMppAuthAudit")
    public Result<Boolean> delete(@Validated @RequestBody WechatBetaMppAuthAuditDeleteParameter parameter) {
        wechatBetaMppAuthAuditService.delete(parameter);
        return new Result<>(true);
    }

}
