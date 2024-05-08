package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatAppFuncScopeDTO;
import com.chia.multienty.core.service.WechatAppFuncScopeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppFuncScopeUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 权限集 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */
@RestController
@RequestMapping("/wechat-app-func-scope")
@RequiredArgsConstructor
@Api(tags = "权限集")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatAppFuncScopeController {
    private final WechatAppFuncScopeService wechatAppFuncScopeService;

    @PostMapping("/detail")
    @ApiOperation("获取权限集详情")
    public Result<WechatAppFuncScopeDTO> getDetail(@RequestBody WechatAppFuncScopeDetailGetParameter parameter) {
        WechatAppFuncScopeDTO detail = wechatAppFuncScopeService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取权限集分页列表")
    public Result<IPage<WechatAppFuncScopeDTO>> getPage(@RequestBody WechatAppFuncScopePageGetParameter parameter) {
        IPage<WechatAppFuncScopeDTO> page = wechatAppFuncScopeService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新权限集")
    @WebLog(target = "WechatAppFuncScope")
    public Result<Boolean> update(@RequestBody WechatAppFuncScopeUpdateParameter parameter) {
        wechatAppFuncScopeService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存权限集")
    @WebLog(target = "WechatAppFuncScope")
    public Result<Boolean> save(@RequestBody WechatAppFuncScopeSaveParameter parameter) {
        wechatAppFuncScopeService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除权限集")
    @WebLog(target = "WechatAppFuncScope")
    public Result<Boolean> delete(@RequestBody WechatAppFuncScopeDeleteParameter parameter) {
        wechatAppFuncScopeService.delete(parameter);
        return new Result<>(true);
    }

}
