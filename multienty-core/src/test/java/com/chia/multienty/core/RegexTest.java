package com.chia.multienty.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexTest {
    @Test
    public void test() {
        String url = "jdbc:mysql://kutashop.cn:3301/ks_master?autoReconnect=true&useUnicode=true&allowMultiQueries=true&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
        String pattern = "jdbc\\:mysql\\:\\/\\/[\\w\\.]+\\:\\d+/(\\w+?)\\?";
        Pattern compile = Pattern.compile(pattern);
        Matcher m = compile.matcher(url);
        if(m.find()) {
            log.info(m.group(0));
            log.info(m.group(1));
        } else {
            log.info("未找到任何匹配");
        }
    }
    @Test
    public void testStringUtil() throws Exception {

        String pattern = String.format("^%s_\\d{6}$", "saas_order");
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher("saas_order_202502");
        Assert.assertTrue(matcher.find());
//        String datetime = "2021-04-30T18:58:48+08:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        LocalDateTime formatted = LocalDateTime.parse(datetime, formatter);
//        log.info(TimeUtil.to19(formatted));
    }
}
