package com.chia.multienty.core.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TimeUtilTest {

    @Test
    void parseISOTime() {
        String dateTime = "2015-05-20T13:29:35+08:00";
        log.info(TimeUtil.parseISOOffsetTime(dateTime).toString());
    }
}