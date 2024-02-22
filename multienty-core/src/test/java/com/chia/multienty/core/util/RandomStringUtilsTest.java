package com.chia.multienty.core.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

@Slf4j
class RandomStringUtilsTest {

    @Test
    void getRandomCode() throws NoSuchAlgorithmException {
        for(int i=0;i<50;i++) {
            log.info("随机字符串:{}",RandomStringUtils.getRandomCode(6, 4));
        }
    }

    @Test
    void getRandom() {
    }
}