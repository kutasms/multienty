package com.chia.multienty.core.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface MultientyUserService {
    UserDetails findByUsername(String username);
}
