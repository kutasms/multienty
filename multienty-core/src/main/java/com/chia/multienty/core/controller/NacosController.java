package com.chia.multienty.core.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * nacos前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Api(tags = "nacos前端控制器")
@ConditionalOnProperty(prefix = "spring.cloud.nacos", name = "config")
public class NacosController {

}
