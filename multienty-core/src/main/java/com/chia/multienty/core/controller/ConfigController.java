package com.chia.multienty.core.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据字典前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
@Api(tags = "配置前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class ConfigController {

}
