package com.chia.multienty.core.controller;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.mybatis.generator.CodeAutoGenerator;
import com.chia.multienty.core.mybatis.generator.vue.VueCodeAutoGenerator;
import com.chia.multienty.core.properties.KutaNacosProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 代码生成器前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-02
 */
@RestController
@RequestMapping("/code")
@Api(tags = "代码生成器前端控制器")
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "dev")
public class CodeGeneratorController {

    @Autowired
    private CodeAutoGenerator codeAutoGenerator;

    @Autowired(required = false)
    private VueCodeAutoGenerator vueCodeAutoGenerator;

    @Autowired
    private KutaNacosProperties kutaNacosProperties;

    @GetMapping("/generate")
    public Result<Boolean> generate() {
        codeAutoGenerator.generate();
        return new Result<>(true);
    }

    @GetMapping("/vue/generate")    public Result<Boolean> generateVueCode() {
        vueCodeAutoGenerator.generate();
        return new Result<>(true);
    }
}
