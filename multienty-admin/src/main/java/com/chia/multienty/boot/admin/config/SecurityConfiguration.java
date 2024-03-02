package com.chia.multienty.boot.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final String contextPath;

    public SecurityConfiguration(AdminServerProperties properties) {
        this.contextPath = properties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(contextPath.isEmpty() ? "/" : contextPath);
        http.authorizeRequests()
                .antMatchers(contextPath + "/assets/**").permitAll()
                .antMatchers(contextPath + "/login", "/css/**","/js/**","/image/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(contextPath + "/login").successHandler(successHandler)
                .and()
                .logout().logoutUrl(contextPath + "/logout")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        "/instances",
                        "/actuator/**"
                )
        ;
    }
}
