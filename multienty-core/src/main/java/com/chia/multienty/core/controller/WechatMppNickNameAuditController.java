package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.service.WechatMppNickNameAuditService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 微信小程序昵称审核单 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */
@RestController
@RequestMapping("/wechat-mpp-nick-name-audit")
@RequiredArgsConstructor
@Api(tags = "微信小程序昵称审核单")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatMppNickNameAuditController {
    private final WechatMppNickNameAuditService wechatMppNickNameAuditService;

    @PostMapping("/detail")
    @ApiOperation("获取微信小程序昵称审核单详情")
    public Result<WechatMppNickNameAuditDTO> getDetail(@RequestBody WechatMppNickNameAuditDetailGetParameter parameter) {
        WechatMppNickNameAuditDTO detail = wechatMppNickNameAuditService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信小程序昵称审核单分页列表")
    public Result<IPage<WechatMppNickNameAuditDTO>> getPage(@RequestBody WechatMppNickNameAuditPageGetParameter parameter) {
        IPage<WechatMppNickNameAuditDTO> page = wechatMppNickNameAuditService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信小程序昵称审核单")
    @WebLog(target = "WechatMppNickNameAudit")
    public Result<Boolean> update(@RequestBody WechatMppNickNameAuditUpdateParameter parameter) {
        wechatMppNickNameAuditService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信小程序昵称审核单")
    @WebLog(target = "WechatMppNickNameAudit")
    public Result<Boolean> save(@RequestBody WechatMppNickNameAuditSaveParameter parameter) {
        wechatMppNickNameAuditService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信小程序昵称审核单")
    @WebLog(target = "WechatMppNickNameAudit")
    public Result<Boolean> enable(@RequestBody WechatMppNickNameAuditEnableParameter parameter) {
        wechatMppNickNameAuditService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信小程序昵称审核单")
    @WebLog(target = "WechatMppNickNameAudit")
    public Result<Boolean> disable(@RequestBody WechatMppNickNameAuditDisableParameter parameter) {
        wechatMppNickNameAuditService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信小程序昵称审核单")
    @WebLog(target = "WechatMppNickNameAudit")
    public Result<Boolean> delete(@RequestBody WechatMppNickNameAuditDeleteParameter parameter) {
        wechatMppNickNameAuditService.delete(parameter);
        return new Result<>(true);
    }

}
