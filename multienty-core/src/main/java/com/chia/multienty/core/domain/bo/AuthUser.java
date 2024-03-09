package com.chia.multienty.core.domain.bo;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {
    private Long userId;
    /**
     * 头像
     * */
    private String avatar;
    /**
     * 帐号
     * */
    private String username;

    /**
     * 姓名
     */
    private String name;

    private String password;

    private Boolean superAdmin;

    /**
     * 角色列表
     * */
    private List<String> roles;
    /**
     * 权限列表
     * */
    private List<String> permissions;

    private Boolean enabled;
    private Boolean expired;
    private Boolean locked;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
