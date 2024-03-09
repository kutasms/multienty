package com.chia.multienty.oauth.handler;

import com.chia.multienty.core.tools.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Primary
@RequiredArgsConstructor
public class TokenAuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenProvider tokenProvider;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> tokenProvider.getAuthentication(auth.getPrincipal().toString()));
//                .map(claims -> {
//                    return new UsernamePasswordAuthenticationToken(
//                        tokenProvider.getUsername(claims),
//                        null,
//                        null);
//                    }
//                );
    }
}
