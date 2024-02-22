package com.chia.multienty.core.controller;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
/**
 * <p>
 * 日历前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
@Api(tags = "日历前端控制器")
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant", name = "base-module-enabled", havingValue = "true")
public class CalendarController {

}
