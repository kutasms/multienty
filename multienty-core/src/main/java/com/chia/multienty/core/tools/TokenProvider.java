package com.chia.multienty.core.tools;

import cn.hutool.core.util.IdUtil;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
@Slf4j
@Configuration
public class TokenProvider implements InitializingBean {

    @Autowired
    private YamlMultiTenantProperties properties;

    private final String CLAIM_KEY = "TOKEN_CLAIM_";

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
                .signWith(key, SignatureAlgorithm.HS512);
    }

    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     * @return /
     */
    public String createToken(Long userId, String username, String data) {
        return MultiTenantContext.getAppType().getTokenPrefix() + jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .setId(IdUtil.simpleUUID())
                .addClaims(
                        MapBuilder.<String, Object>create()
                                .add(CLAIM_KEY + "UID", userId)
                                .add(CLAIM_KEY + "NAME", username)
                                .add(CLAIM_KEY + "DATA", data)
                                .get())
                .setSubject(userId.toString())
                .compact();
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

    public String getToken(HttpServletRequest request, String prefix) {
        final String header = request.getHeader(properties.getSecurity().getAuth().getHeader());
        if (header != null) {
            if(header.startsWith(prefix)) {
                return header.substring(prefix.length());
            } else {
                throw new KutaRuntimeException(HttpResultEnum.ILLEGAL_TOKEN);
            }
        }
        return null;
    }

    public String getToken(String token, String prefix) {
        if (token != null && token.startsWith(prefix)) {
            return token.substring(prefix.length());
        }
        return token;
    }
}
