package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.DictDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.base.DictDeleteParameter;
import com.chia.multienty.core.parameter.base.DictListGetParameter;
import com.chia.multienty.core.parameter.base.DictSaveParameter;
import com.chia.multienty.core.parameter.base.DictUpdateParameter;
import com.chia.multienty.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/dict")
@RequiredArgsConstructor
@Api(tags = "数据字典前端控制器")
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant", name = "base-module-enabled", havingValue = "true")
public class DictController {
    
    @Autowired
    private final DictService dictService;
    
    @PostMapping("page")
    @ApiOperation(value = "获取字典分页列表")
    public Result<IPage<DictDTO>> getPage(@RequestBody DictListGetParameter parameter) {
        IPage<DictDTO> list = dictService.getList(parameter);
        return new Result<>(list, HttpResultEnum.SUCCESS.getCode());
    }


    @PostMapping("save")
    @ApiOperation(value = "保存字典")
    @WebLog
    public Result<Boolean> save(@RequestBody DictSaveParameter parameter) throws CryptoException {
        Boolean result = dictService.save(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }

    @PostMapping("update")
    @ApiOperation(value = "更新字典")
    @WebLog
    public Result<Boolean> update(@RequestBody DictUpdateParameter parameter) throws CryptoException {
        Boolean result = dictService.update(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }

    @PostMapping("delete")
    @ApiOperation(value = "删除字典")
    @WebLog
    public Result<Boolean> delete(@RequestBody DictDeleteParameter parameter) throws KutaRuntimeException {
        Boolean result = dictService.deleteSafely(parameter.getDictId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
}
