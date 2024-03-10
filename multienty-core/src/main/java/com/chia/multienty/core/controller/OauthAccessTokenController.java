package com.chia.multienty.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.service.OauthAccessTokenService;
import com.chia.multienty.core.domain.dto.OauthAccessTokenDTO;
import com.chia.multienty.core.pojo.OauthAccessToken;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenDetailGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenPageGetParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenDeleteParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthAccessTokenUpdateParameter;
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
 * 访问令牌 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@RestController
@Validated
@RequestMapping("/oauth-access-token")
@RequiredArgsConstructor
@Api(tags = "访问令牌")
public class OauthAccessTokenController {
    private final OauthAccessTokenService oauthAccessTokenService;

    @PostMapping("/detail")
    @ApiOperation("获取访问令牌详情")
    public Result<OauthAccessTokenDTO> getDetail(@Validated @RequestBody OauthAccessTokenDetailGetParameter parameter) {
        OauthAccessTokenDTO detail = oauthAccessTokenService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取访问令牌分页列表")
    public Result<IPage<OauthAccessTokenDTO>> getPage(@Validated @RequestBody OauthAccessTokenPageGetParameter parameter) {
        IPage<OauthAccessTokenDTO> page = oauthAccessTokenService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新访问令牌")
    @WebLog
    public Result<Boolean> update(@Validated @RequestBody OauthAccessTokenUpdateParameter parameter) {
        oauthAccessTokenService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存访问令牌")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody OauthAccessTokenSaveParameter parameter) {
        oauthAccessTokenService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除访问令牌")
    @WebLog
    public Result<Boolean> delete(@Validated @RequestBody OauthAccessTokenDeleteParameter parameter) {
        oauthAccessTokenService.delete(parameter);
        return new Result<>(true);
    }

}
