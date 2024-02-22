package com.chia.multienty.core.controller;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.parameter.base.TableConstructCopyParameter;
import com.chia.multienty.core.tools.TableCopier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * sql生成服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/sql-generate")
@Api(tags = "sql生成服务")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "dev")
public class SqlGenerateController {
    private final TableCopier tableCopier;

    @ApiOperation("复制表格结构")
    @PostMapping("copy-construct")
    public Result<Boolean> copyTableConstruct(@RequestBody TableConstructCopyParameter parameter) {
        tableCopier.copyTableConstruct(parameter.getSourceTableName(),
                parameter.getTargetTableName(),
                parameter.getAutoIncrement());
        return new Result<>(true);
    }
}
