package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.service.WechatMppRegisterAuditService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 微信小程序代码审核单 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */
@RestController
@RequestMapping("/wechat-mpp-register-audit")
@RequiredArgsConstructor
@Api(tags = "微信小程序代码审核单")
public class WechatMppRegisterAuditController {
    private final WechatMppRegisterAuditService wechatMppRegisterAuditService;

    @PostMapping("/detail")
    @ApiOperation("获取微信小程序代码审核单详情")
    public Result<WechatMppRegisterAuditDTO> getDetail(@RequestBody WechatMppRegisterAuditDetailGetParameter parameter) {
        WechatMppRegisterAuditDTO detail = wechatMppRegisterAuditService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信小程序代码审核单分页列表")
    public Result<IPage<WechatMppRegisterAuditDTO>> getPage(@RequestBody WechatMppRegisterAuditPageGetParameter parameter) {
        IPage<WechatMppRegisterAuditDTO> page = wechatMppRegisterAuditService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信小程序代码审核单")
    @WebLog
    public Result<Boolean> update(@RequestBody WechatMppRegisterAuditUpdateParameter parameter) {
        wechatMppRegisterAuditService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信小程序代码审核单")
    @WebLog
    public Result<Boolean> save(@RequestBody WechatMppRegisterAuditSaveParameter parameter) {
        wechatMppRegisterAuditService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信小程序代码审核单")
    @WebLog
    public Result<Boolean> enable(@RequestBody WechatMppRegisterAuditEnableParameter parameter) {
        wechatMppRegisterAuditService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信小程序代码审核单")
    @WebLog
    public Result<Boolean> save(@RequestBody WechatMppRegisterAuditDisableParameter parameter) {
        wechatMppRegisterAuditService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信小程序代码审核单")
    @WebLog
    public Result<Boolean> delete(@RequestBody WechatMppRegisterAuditDeleteParameter parameter) {
        wechatMppRegisterAuditService.delete(parameter);
        return new Result<>(true);
    }

}
