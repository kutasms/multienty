package com.chia.multienty.oauth.controller;

import com.aliyuncs.exceptions.ClientException;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.LoginAccountType;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.dubbo.service.DubboWxOAuthService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.user.LoginParameter;
import com.chia.multienty.core.parameter.user.LoginVerificationCodeSendParameter;
import com.chia.multienty.core.parameter.user.LogoutParameter;
import com.chia.multienty.core.service.TenantService;
import com.chia.multienty.core.service.UserService;
import com.chia.multienty.core.service.impl.DelegatingUserDetailsServiceImpl;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RequestMapping("/oauth")
@RestController
public class OAuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private TenantService tenantService;

    @Autowired(required = false)
    private DubboWxOAuthService dubboWxOAuthService;

    @Autowired(required = false)
    private DelegatingUserDetailsServiceImpl delegatingUserDetailsService;
    @Autowired
    private StringRedisService stringRedisService;
    @PostMapping("access_token_get")
    public Mono<Result<LoginResult>> login(@RequestBody LoginParameter parameter, ServerWebExchange exchange) throws Exception {
        String appId = exchange.getRequest().getHeaders().getFirst(MultientyHeaderConstants.APP_ID_KEY);
        ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
        switch (applicationType) {
            case MERCHANT:
                return tenantService.login(parameter);
            case WECHAT_MPP:
                if(dubboWxOAuthService == null) {
                    throw new KutaRuntimeException(HttpResultEnum.PATTERN_SERVICE_NOT_IMPLEMENT, "微信小程序登录");
                }
                delegatingUserDetailsService.setApplicationType(ApplicationType.WECHAT_MPP, LoginAccountType.MAIN_ACCOUNT);
                return Mono.just(dubboWxOAuthService.login(parameter));
            default:
                return userService.login(parameter);
        }
    }
    @GetMapping("get_info")
    public Mono<Result<String>> getInfo() {
        return Mono.just(new Result<>("OAuth2 Service Application"));
    }

    @GetMapping("logout")
    public Mono<Result<Void>> logout(ServerWebExchange exchange) {
        String appId = exchange.getRequest().getHeaders().getFirst(MultientyHeaderConstants.APP_ID_KEY);
        ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
        switch (applicationType) {
            case MERCHANT:
                 tenantService.logout(
                        new LogoutParameter()
                                .setToken(exchange.getRequest().getHeaders().getFirst(MultientyHeaderConstants.TOKEN_KEY)));
            case PLATFORM:
                userService.logout(
                        new LogoutParameter()
                            .setToken(exchange.getRequest().getHeaders().getFirst(MultientyHeaderConstants.TOKEN_KEY)));
        }
        return Mono.just(new Result<>());
    }

    @PostMapping("send_verify_code")
    public Mono<Result> sendSMSCode(@RequestBody LoginVerificationCodeSendParameter parameter, ServerWebExchange exchange) throws ClientException {
        String appId = exchange.getRequest().getHeaders().getFirst(MultientyHeaderConstants.APP_ID_KEY);
        if(appId == null) {
            throw new KutaRuntimeException(HttpResultEnum.HEADER_LOSE_PATTERN, MultientyHeaderConstants.APP_ID_KEY);
        }
        ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
        SMSResult smsResult = null;
        switch (applicationType) {
            case MERCHANT:
                smsResult = tenantService.sendVerificationCode(parameter);
                break;
            case PLATFORM:
                smsResult = userService.sendVerificationCode(parameter);
                break;
            default:
                throw new KutaRuntimeException(HttpResultEnum.HEADER_ERROR_PATTERN, MultientyHeaderConstants.APP_ID_KEY);
        }
        if(smsResult.getSucceeded()) {
            return Mono.just(new Result(HttpResultEnum.SUCCESS));
        } else {
            return Mono.just(new Result(HttpResultEnum.FAILURE));
        }
    }


}
