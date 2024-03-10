package com.chia.multienty.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.service.OauthClientDetailsService;
import com.chia.multienty.core.domain.dto.OauthClientDetailsDTO;
import com.chia.multienty.core.pojo.OauthClientDetails;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsDetailGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsPageGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsDeleteParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthClientDetailsUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 客户端信息 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@RestController
@Validated
@RequestMapping("/oauth-client-details")
@RequiredArgsConstructor
@Api(tags = "客户端信息")
public class OauthClientDetailsController {
    private final OauthClientDetailsService oauthClientDetailsService;

    @PostMapping("/detail")
    @ApiOperation("获取客户端信息详情")
    public Result<OauthClientDetailsDTO> getDetail(@Validated @RequestBody OauthClientDetailsDetailGetParameter parameter) {
        OauthClientDetailsDTO detail = oauthClientDetailsService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取客户端信息分页列表")
    public Result<IPage<OauthClientDetailsDTO>> getPage(@Validated @RequestBody OauthClientDetailsPageGetParameter parameter) {
        IPage<OauthClientDetailsDTO> page = oauthClientDetailsService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新客户端信息")
    @WebLog
    public Result<Boolean> update(@Validated @RequestBody OauthClientDetailsUpdateParameter parameter) {
        oauthClientDetailsService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存客户端信息")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody OauthClientDetailsSaveParameter parameter) {
        oauthClientDetailsService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除客户端信息")
    @WebLog
    public Result<Boolean> delete(@Validated @RequestBody OauthClientDetailsDeleteParameter parameter) {
        oauthClientDetailsService.delete(parameter);
        return new Result<>(true);
    }

}
