package com.chia.multienty.core.tools;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.IdUtil;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.exception.InvalidJwtAuthenticationException;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@Configuration
public class TokenProvider implements InitializingBean {

    @Autowired
    private YamlMultientyProperties properties;

    private final String CLAIM_KEY = "TOKEN_CLAIM_";
    private final String CLAIM_USER_NAME = CLAIM_KEY + "USERNAME";
    private final String CLAIM_PERMISSIONS = CLAIM_KEY + "PERMS";
    private final String CLAIM_APP_TYPE = CLAIM_KEY + "APP_TYPE";

    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecurity().getAuth().getBase64Secret());
        Key key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        jwtBuilder = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256);
    }

    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     * @return /
     */
    public String createToken(Long userId, String username, List<String> roles) {
        return jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .addClaims(
                        MapBuilder.<String, Object>create()
                                .put(CLAIM_USER_NAME, username)
                                .put(CLAIM_PERMISSIONS, roles.stream().collect(joining(",")))
                                .build())
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        properties.getSecurity().getAuth().getAccessTokenExpired() * 1000))
                .compact();
    }

    public String createToken(Authentication authentication) {
        LoggedUserVO user = (LoggedUserVO) authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Claims claims = Jwts.claims().setSubject(user.getUserId().toString());
        claims.put(CLAIM_USER_NAME, user.getUsername());
        claims.put(CLAIM_APP_TYPE, user.getApplicationType().getValue());
//        if (!authorities.isEmpty()) {
//            claims.put(CLAIM_PERMISSIONS, authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
//        }
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        properties.getSecurity().getAuth().getAccessTokenExpired() * 1000))
                .signWith(SignatureAlgorithm.HS256, properties.getSecurity().getAuth().getBase64Secret())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        ApplicationType applicationType = ApplicationType.valueOf(claims.get(CLAIM_APP_TYPE, Long.class));
//        Collection<? extends GrantedAuthority> authorities = permissions == null ? AuthorityUtils.NO_AUTHORITIES
//                : AuthorityUtils.commaSeparatedStringToAuthorityList(permissions);
        LoggedUserVO principal = LoggedUserVO.builder()
                .username(claims.get(CLAIM_USER_NAME).toString())
                .userId(getUserId(claims))
                .applicationType(applicationType)
//                .joiningRoles(permissions)
                .build();
        return new UsernamePasswordAuthenticationToken(principal, token, null);
    }
    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     * @return /
     */
    public String createRefreshToken(Long userId, String username) {
        return jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .addClaims(
                        MapBuilder.<String, Object>create()
                                .put(CLAIM_USER_NAME, username)
                                .build())
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        properties.getSecurity().getAuth().getRefreshTokenExpired() * 1000))
                .compact();
    }


    public Object getClaim(Claims claims, String key) {
        return claims.get(CLAIM_KEY + key);
    }

    public <T> T getClaim(Claims claims, String key, Class<T> clazz) {
        return claims.get(CLAIM_KEY + key, clazz);
    }

    public String getUsername(Claims claims) {
        return claims.get(CLAIM_USER_NAME, String.class);
    }

    public Long getUserId(Claims claims) {
        return Long.parseLong(claims.getSubject());
    }

    public String getRoles(Claims claims) {
        return claims.get(CLAIM_PERMISSIONS, String.class);
    }

    public ApplicationType getAppType(Claims claims) {
        return ApplicationType.valueOf(claims.get(CLAIM_APP_TYPE, Long.class));
    }

    public Collection<? extends GrantedAuthority > getAuthorities(Claims claims) {
        String roles = getRoles(claims);
        return roles == null ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token /
     * @return /
     */
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        if(claims == null) {
            return null;
        }
        return Long.parseLong(claims.getSubject());
    }

    public Claims getClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证token是否有效
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try{
            Claims claims = getClaims(token);
            if(claims.getExpiration().before(new Date())) {
                return false;
            }
            return true;
        }
        catch (JwtException | IllegalArgumentException ex) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
