package com.chia.multienty.core.infra.controller;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.infra.registercenter.nacos.NacosService;
import com.chia.multienty.core.parameter.base.YamlGetParameter;
import com.chia.multienty.core.parameter.base.YamlUpdateParameter;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 数据字典前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/nacos")
@RequiredArgsConstructor
@Api(tags = "配置前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class NacosController {
    private final NacosService nacosService;

    private final String MULTIENTY_DATA_ID = "multienty.yml";

    @PostMapping("getMultientyConfig")
    public Result<Map> getMultientyConfig() {
        Map<String, Object> object = nacosService.getObject(MULTIENTY_DATA_ID);
        return new Result<>(object);
    }

    @PostMapping("updateMultientyConfig")
    public Result updateMultientyConfig(@RequestBody YamlUpdateParameter parameter) {
        nacosService.updateYamlConfig(parameter.getYaml(), MULTIENTY_DATA_ID);
        return new Result();
    }

    @PostMapping("getSpecConfig")
    public Result<Map> getSpecConfig(@RequestBody YamlGetParameter parameter) {
        Map<String, Object> object = null;
        if(parameter.getGroup() == null) {
            object = nacosService.getObject(parameter.getDataId());
        } else {
            object = nacosService.getObject(parameter.getDataId(), parameter.getGroup());
        }
        return new Result<>(object);
    }

    @PostMapping("getSpecConfigStr")
    public Result<String> getSpecConfigStr(@RequestBody YamlGetParameter parameter) {
        String object = null;
        if(parameter.getGroup() == null) {
            object = nacosService.getObjectStr(parameter.getDataId());
        } else {
            object = nacosService.getObjectStr(parameter.getDataId(), parameter.getGroup());
        }
        return new Result<>(object);
    }

    @PostMapping("updateSpecConfig")
    public Result updateSpecConfig(@RequestBody YamlUpdateParameter parameter) {
        nacosService.updateYamlConfig(parameter.getYaml(), parameter.getDataId(), parameter.getGroup());
        return new Result();
    }

}
