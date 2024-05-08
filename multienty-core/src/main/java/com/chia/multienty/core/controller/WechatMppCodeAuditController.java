package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatMppCodeAuditDTO;
import com.chia.multienty.core.service.WechatMppCodeAuditService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditDisableParameter;
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
@RequestMapping("/wechat-mpp-code-audit")
@RequiredArgsConstructor
@Api(tags = "微信小程序代码审核单")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatMppCodeAuditController {
    private final WechatMppCodeAuditService wechatMppCodeAuditService;

    @PostMapping("/detail")
    @ApiOperation("获取微信小程序代码审核单详情")
    public Result<WechatMppCodeAuditDTO> getDetail(@RequestBody WechatMppCodeAuditDetailGetParameter parameter) {
        WechatMppCodeAuditDTO detail = wechatMppCodeAuditService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信小程序代码审核单分页列表")
    public Result<IPage<WechatMppCodeAuditDTO>> getPage(@RequestBody WechatMppCodeAuditPageGetParameter parameter) {
        IPage<WechatMppCodeAuditDTO> page = wechatMppCodeAuditService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信小程序代码审核单")
    @WebLog(target = "WechatMppCodeAudit")
    public Result<Boolean> update(@RequestBody WechatMppCodeAuditUpdateParameter parameter) {
        wechatMppCodeAuditService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信小程序代码审核单")
    @WebLog(target = "WechatMppCodeAudit")
    public Result<Boolean> save(@RequestBody WechatMppCodeAuditSaveParameter parameter) {
        wechatMppCodeAuditService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信小程序代码审核单")
    @WebLog(target = "WechatMppCodeAudit")
    public Result<Boolean> enable(@RequestBody WechatMppCodeAuditEnableParameter parameter) {
        wechatMppCodeAuditService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信小程序代码审核单")
    @WebLog(target = "WechatMppCodeAudit")
    public Result<Boolean> disable(@RequestBody WechatMppCodeAuditDisableParameter parameter) {
        wechatMppCodeAuditService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信小程序代码审核单")
    @WebLog(target = "WechatMppCodeAudit")
    public Result<Boolean> delete(@RequestBody WechatMppCodeAuditDeleteParameter parameter) {
        wechatMppCodeAuditService.delete(parameter);
        return new Result<>(true);
    }

}
