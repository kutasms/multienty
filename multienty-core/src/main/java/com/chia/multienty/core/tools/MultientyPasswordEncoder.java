package com.chia.multienty.core.tools;

import com.chia.multienty.core.util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MultientyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.md5WithSalt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return MD5Util.verify(rawPassword.toString(), encodedPassword);
    }
}
