package com.chia.multienty.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.basic.PagedParameter;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.service.ComponentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 组件前端控制器
 */
@RestController
@RequestMapping("component")
@Slf4j(topic = "ComponentController")
@Api(tags = "组件前端控制器")
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant", name = "base-module-enabled", havingValue = "true")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @PostMapping("icon/list")
    @ApiOperation(value = "获取图标列表")
    public Result<IPage<String>> getIconList(@RequestBody PagedParameter<String> parameter, HttpServletRequest request) {
        IPage<String> icons = componentService.getIcons(parameter);
        return new Result<>(icons, HttpResultEnum.SUCCESS.getCode());
    }

}
