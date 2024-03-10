package com.chia.multienty.core.domain.vo;

import com.chia.multienty.core.domain.dto.MultientyGrantedAuthority;
import com.chia.multienty.core.domain.dto.PermissionDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.util.ListUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 已登录用户VO
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserVO implements UserDetails {

    private Long userId;
    /**
     * 头像
     * */
    private String avatar;
    /**
     * 帐号
     * */
    private String username;

    private String phoneNumber;

    /**
     * 姓名
     */
    private String name;

    private Boolean superAdmin;

    /**
     * 角色列表
     * */
    private String joiningRoles;
    /**
     * 权限列表
     * */
    private List<PermissionDTO> permissions;

    private String password;

    private Boolean enabled;
    private Boolean expired;
    private Boolean locked;
    /**
     * 应用类型
     */
    private ApplicationType applicationType;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(!ListUtil.isEmpty(this.permissions)) {
            return this.permissions.stream().map(m ->
                    new MultientyGrantedAuthority(m.getApi())
            ).collect(Collectors.toList());
        }  else {
            return new ArrayList<>();
        }
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return getExpired() == null || !getExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return getLocked() == null || !getLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return getExpired() == null || !getExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    public LoggedUserVO(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.permissions = userDTO.getPermissions();
        this.userId = userDTO.getUserId();
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
        this.phoneNumber = userDTO.getPhone();
        this.joiningRoles = userDTO.getRoleAlias();
        this.avatar = userDTO.getAvatar();
        this.enabled = StatusEnum.NORMAL.getCode().equals(userDTO.getStatus());
        this.expired = userDTO.getExpired() == null ? false: userDTO.getExpired();
        this.locked = userDTO.getLocked() == null ? false: userDTO.getLocked();
    }


    public LoggedUserVO(TenantDTO userDTO) {
        this.username = userDTO.getUsername();
        this.permissions = userDTO.getPermissions();
        this.name = userDTO.getCompanyName();
        this.userId = userDTO.getUserId();
        this.password = userDTO.getPassword();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.joiningRoles = userDTO.getRoleAlias();
        this.avatar = userDTO.getAvatar();
        this.enabled = StatusEnum.NORMAL.getCode().equals(userDTO.getStatus());
        this.expired = userDTO.getExpired() == null ? false: userDTO.getExpired();
        this.locked = userDTO.getLocked() == null ? false: userDTO.getLocked();
    }
}
