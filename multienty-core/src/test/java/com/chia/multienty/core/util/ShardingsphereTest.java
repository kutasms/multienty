package com.chia.multienty.core.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.util.expr.InlineExpressionParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ShardingsphereTest {
    @Test
    public void test() {
        String expression = "ds_order_${1..2}.ks_order_${2023..2030}${(1..12).collect{t -> t.toString().padLeft(2, '0')}}";
        InlineExpressionParser parser = new InlineExpressionParser(expression);
        List<String> strings = parser.splitAndEvaluate();
        strings = strings.stream().map(m->m.split("\\.")[1]).collect(Collectors.toList());
        log.info(JSONObject.toJSONString(strings));
    }
}
