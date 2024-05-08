package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.wechat.thirdparty.parameter.template.AddToTemplateParameter;
import com.chia.multienty.wechat.thirdparty.parameter.template.TemplateDeleteParameter;
import com.chia.multienty.wechat.thirdparty.parameter.template.TemplateListGetParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.AddToTemplateResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateDeleteResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateListGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplatedRaftListGetResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台模版管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/template")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台模版管理前端控制器")
public class WxTPTemplateController {
    private final WxTPTemplateService wxTPTemplateService;

    @ApiOperation("将草稿添加到模板库")
    @PostMapping("addToTemplate")
    public Result<AddToTemplateResponse> addToTemplate(@RequestBody AddToTemplateParameter parameter) {
        AddToTemplateResponse response = wxTPTemplateService.addToTemplate(
                parameter.getTemplateType(), parameter.getDraftId());
        return new Result<>(response);
    }
    @ApiOperation("删除代码模版")
    @PostMapping("deleteTemplate")
    public Result<TemplateDeleteResponse> deleteTemplate(@RequestBody TemplateDeleteParameter parameter) {
        TemplateDeleteResponse response = wxTPTemplateService.deleteTemplate(parameter.getTemplateId());
        return new Result<>(response);
    }
    @ApiOperation("获取草稿箱列表")
    @PostMapping("getTemplatedRaftList")
    public Result<TemplatedRaftListGetResponse> getTemplatedRaftList() {
        TemplatedRaftListGetResponse response = wxTPTemplateService.getTemplatedRaftList();
        return new Result<>(response);
    }
    @ApiOperation("获取模板列表")
    @PostMapping("getTemplateList")
    public Result<TemplateListGetResponse> getTemplateList(@RequestBody TemplateListGetParameter parameter) {
        TemplateListGetResponse response = wxTPTemplateService.getTemplateList(parameter.getTemplateType());
        return new Result<>(response);
    }
}
