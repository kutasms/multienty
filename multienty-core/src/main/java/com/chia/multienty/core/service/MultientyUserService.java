package com.chia.multienty.core.service;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface MultientyUserService {
    Mono<UserDetails> findByUsername(String username);
}
