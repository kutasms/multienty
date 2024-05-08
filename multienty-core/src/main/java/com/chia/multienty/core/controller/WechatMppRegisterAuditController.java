package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.service.WechatMppRegisterAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 微信小程序注册审核单 服务
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@RestController
@Validated
@RequestMapping("/wechat-mpp-register-audit")
@RequiredArgsConstructor
@Api(tags = "微信小程序注册审核单")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatMppRegisterAuditController {

    private final WechatMppRegisterAuditService wechatMppRegisterAuditService;

    @PostMapping("/detail")
    @ApiOperation("获取微信小程序注册审核单详情")
    public Result<WechatMppRegisterAuditDTO> getDetail(@Validated @RequestBody WechatMppRegisterAuditDetailGetParameter parameter) {
        WechatMppRegisterAuditDTO detail = wechatMppRegisterAuditService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信小程序注册审核单分页列表")
    public Result<IPage<WechatMppRegisterAuditDTO>> getPage(@Validated @RequestBody WechatMppRegisterAuditPageGetParameter parameter) {
        IPage<WechatMppRegisterAuditDTO> page = wechatMppRegisterAuditService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信小程序注册审核单")
    @WebLog(target = "WechatMppRegisterAudit")
    public Result<Boolean> update(@Validated @RequestBody WechatMppRegisterAuditUpdateParameter parameter) {
        wechatMppRegisterAuditService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信小程序注册审核单")
    @WebLog(target = "WechatMppRegisterAudit")
    public Result<Boolean> save(@Validated @RequestBody WechatMppRegisterAuditSaveParameter parameter) {
        wechatMppRegisterAuditService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信小程序注册审核单")
    @WebLog(target = "WechatMppRegisterAudit")
    public Result<Boolean> enable(@Validated @RequestBody WechatMppRegisterAuditEnableParameter parameter) {
        wechatMppRegisterAuditService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信小程序注册审核单")
    @WebLog(target = "WechatMppRegisterAudit")
    public Result<Boolean> disable(@Validated @RequestBody WechatMppRegisterAuditDisableParameter parameter) {
        wechatMppRegisterAuditService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信小程序注册审核单")
    @WebLog(target = "WechatMppRegisterAudit")
    public Result<Boolean> delete(@Validated @RequestBody WechatMppRegisterAuditDeleteParameter parameter) {
        wechatMppRegisterAuditService.delete(parameter);
        return new Result<>(true);
    }

}
