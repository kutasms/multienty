package com.chia.multienty.core.domain.bo;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WechatAuthentication extends AbstractAuthenticationToken {
    private Object principal;
    private Object credentials;

    private Collection<? extends GrantedAuthority> authorities;

    public WechatAuthentication(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public WechatAuthentication(Object principal, Object credentials,
                                   Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }


    @Override
    public Object getPrincipal() {
        return principal;
    }
}
