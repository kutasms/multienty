package com.chia.multienty.core.exception;

import com.chia.multienty.core.domain.enums.HttpResultEnum;

public class AuthenticationFailedException extends KutaRuntimeException {

    public AuthenticationFailedException(HttpResultEnum resultEnum) {
        super(resultEnum);
    }
}
