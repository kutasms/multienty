package com.chia.multienty.core.domain.vo;

import com.chia.multienty.core.domain.basic.IWebLogUser;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.dto.*;
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
import java.util.Arrays;
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
public class LoggedUserVO implements UserDetails, IWebLogUser {

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

    /**
     * 是否超级管理员
     */
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
    /**
     * 是否主帐号
     */
    private Boolean isMainAcc = true;

    private Boolean enabled;
    private Boolean expired;
    private Boolean locked;
    private List<String> roles;
    private String joinedRoleIds;

    private Long tenantId;
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
        this.name = userDTO.getName();
        this.userId = userDTO.getLogUserId();
        this.password = userDTO.getPassword();
        this.tenantId = userDTO.getUserId();
        this.phoneNumber = userDTO.getPhone();
        this.superAdmin = userDTO.getSuperAdmin();
        this.joiningRoles = userDTO.getRoleAlias();
        this.avatar = userDTO.getAvatar();
        this.enabled = StatusEnum.NORMAL.getCode().equals(userDTO.getStatus());
        this.expired = userDTO.getExpired() == null ? false: userDTO.getExpired();
        this.locked = userDTO.getLocked() == null ? false: userDTO.getLocked();
        this.roles = Arrays.asList(userDTO.getRoleName().split(","));
    }

    public LoggedUserVO(TenantDTO userDTO) {
        this.username = userDTO.getUsername();
        this.permissions = userDTO.getPermissions();
        this.name = userDTO.getCompanyName();
        this.userId = userDTO.getLogUserId();
        this.password = userDTO.getPassword();
        this.tenantId = userDTO.getTenantId();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.superAdmin = userDTO.getSuperAdmin();
        this.joiningRoles = userDTO.getRoleAlias();
        this.avatar = userDTO.getAvatar();
        this.enabled = StatusEnum.NORMAL.getCode().equals(userDTO.getStatus());
        this.expired = userDTO.getExpired() == null ? false: userDTO.getExpired();
        this.locked = userDTO.getLocked() == null ? false: userDTO.getLocked();
        if(userDTO.getRoleName() == null) {
            userDTO.setRoleName("Admin");
        }
        this.roles = Arrays.asList(userDTO.getRoleName().split(","));
    }

    public LoggedUserVO(TenantSubAccountDTO userDTO) {
        this.username = userDTO.getUsername();
        this.permissions = userDTO.getPermissions();
        this.name = userDTO.getName();
        this.userId = userDTO.getSubAccountId();
        this.tenantId = userDTO.getTenantId();
        this.password = userDTO.getPassword();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.superAdmin = userDTO.getIsSuperAdmin();
        this.joiningRoles = userDTO.getRoleAlias();
        this.avatar = MultientyConstants.DEFAULT_AVATAR;
        this.enabled = StatusEnum.NORMAL.getCode().equals(userDTO.getStatus());
        this.expired = false;
        this.locked = false;
        this.roles = Arrays.asList(userDTO.getRoleName().split(","));
    }



    @Override
    public Long getLogUserId() {
        return this.userId;
    }

    @Override
    public String getLogUserName() {
        return this.name;
    }
}
