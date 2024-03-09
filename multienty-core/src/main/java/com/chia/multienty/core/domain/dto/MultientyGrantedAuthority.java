package com.chia.multienty.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class MultientyGrantedAuthority implements GrantedAuthority {
    private String authority;
}
