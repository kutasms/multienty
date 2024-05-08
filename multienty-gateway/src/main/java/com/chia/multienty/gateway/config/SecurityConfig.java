package com.chia.multienty.gateway.config;

import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.impl.DelegatingUserDetailsServiceImpl;
import com.chia.multienty.core.tools.TokenProvider;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.gateway.tools.DefaultAuthorizationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Configuration
@RequiredArgsConstructor
@ConditionalOnClass({YamlMultientyProperties.class})
public class SecurityConfig implements ApplicationContextAware {

    @Resource
    private DefaultAuthorizationManager defaultAuthorizationManager;

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http,
                                                TokenProvider tokenProvider,
                                                YamlMultientyProperties properties,
                                                ReactiveAuthenticationManager reactiveAuthenticationManager) {

        http
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authenticationManager(reactiveAuthenticationManager)

                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .csrf().disable()
                .logout()
                .disable();

        http
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .and().authorizeExchange()
                .pathMatchers(properties.getSecurity().getIgnorePaths()).permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> {
                    e.printStackTrace();
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return swe.getResponse().writeWith(Mono.just(new DefaultDataBufferFactory().wrap("UNAUTHORIZED".getBytes())));
                })
                .and()
                .exceptionHandling()
                .accessDeniedHandler((swe, e) -> {
                    e.printStackTrace();
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return swe.getResponse().writeWith(Mono.just(new DefaultDataBufferFactory().wrap("FORBIDDEN".getBytes())));
                })
                .and().authorizeExchange()
                .pathMatchers("/**").permitAll();
        ;
        return http.build();
    }
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(new DelegatingUserDetailsServiceImpl());
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setContext(applicationContext);
    }
}
