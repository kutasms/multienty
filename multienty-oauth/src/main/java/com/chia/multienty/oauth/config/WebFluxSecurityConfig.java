package com.chia.multienty.oauth.config;

import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.UserService;
import com.chia.multienty.core.tools.MultientyPasswordEncoder;
import com.chia.multienty.core.tools.PhoneCodeAuthenticationManager;
import com.chia.multienty.core.tools.WechatAuthenticationManager;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.oauth.filter.OAuthFilter;
import com.chia.multienty.oauth.handler.*;
import com.chia.multienty.core.service.impl.DelegatingUserDetailsServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.LinkedList;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig implements ApplicationContextAware {
    @Resource
    private DefaultAuthorizationManager defaultAuthorizationManager;
    @Resource
    private UserService userService;


    @Resource
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Resource
    private DefaultSecurityContextRepository defaultSecurityContextRepository;

    @Resource
    private DefaultAuthenticationEntryPoint defaultAuthenticationEntryPoint;
    @Resource
    private TokenServerLogoutSuccessHandler tokenServerLogoutSuccessHandler;
    @Resource
    private DefaultAccessDeniedHandler defaultAccessDeniedHandler;
    @Resource
    private YamlMultientyProperties properties;
    @Resource
    private DelegatingUserDetailsServiceImpl delegatingUserDetailsService;
    @Resource
    private OAuthFilter oAuthFilter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
         httpSecurity
                 .securityContextRepository(defaultSecurityContextRepository)
                 .authenticationManager(reactiveAuthenticationManager())

                 .httpBasic()
                 .disable()
                 .formLogin()
                 .disable()
                 .csrf().disable()
                 .logout()
                 .disable();

         httpSecurity.addFilterAt(oAuthFilter, SecurityWebFiltersOrder.FIRST);

         httpSecurity
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .and().authorizeExchange()
                .pathMatchers(properties.getSecurity().getIgnorePaths()).permitAll()

//                .authenticationSuccessHandler(defaultAuthenticationSuccessHandler)
//                .authenticationFailureHandler(defaultAuthenticationFailureHandler)
//                .and()
//                .logout()
//                .logoutSuccessHandler(tokenServerLogoutSuccessHandler)
//                .and()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(defaultAuthenticationEntryPoint)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(defaultAccessDeniedHandler)
                .and().authorizeExchange()
                .pathMatchers("/**").access(defaultAuthorizationManager)
                .anyExchange().authenticated();

         return httpSecurity.build();
    }

    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     */
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(authentication -> {
            // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
            return Mono.empty();
        });
        // 手机号验证码登录
        managers.add(new PhoneCodeAuthenticationManager(delegatingUserDetailsService));
        managers.add(new WechatAuthenticationManager(delegatingUserDetailsService));
        // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
        UserDetailsRepositoryReactiveAuthenticationManager userDetailsManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(delegatingUserDetailsService);
        userDetailsManager.setPasswordEncoder(new MultientyPasswordEncoder());
        managers.add(userDetailsManager);
        managers.add(tokenAuthenticationManager);
        return new DelegatingReactiveAuthenticationManager(managers);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setContext(applicationContext);
    }
}
