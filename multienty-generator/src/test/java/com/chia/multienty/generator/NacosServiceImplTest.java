package com.chia.multienty.generator;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.domain.basic.CityVO;
import com.chia.multienty.core.registercenter.nacos.NacosConfig;
import com.chia.multienty.core.registercenter.nacos.NacosService;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.util.MapUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class NacosServiceImplTest {

    @Data
    public class Nick {
        String name;
    }

    @Autowired
    private NacosService nacosService;

    @Test
    void getConfig() {

        NacosConfig config = nacosService.getConfig();
        String result = JSONObject.toJSONString(config, true);
        System.out.print(result);
    }

    @Test
    void getAppConfig() {
        Map<String, Object> map = nacosService.getObject("edit_test.yaml", "test");
        System.out.print(JSONObject.toJSONString(map));
    }

    @Test
    void updateYamlConfig() {
        Map<String, Object> map = nacosService.getObject("edit_test.yaml", "test");
        Map<String, Object> map2 = (Map<String, Object>) map.get("spring");
        map2.put("client", "DTO2");
        CityVO city = MapUtil.get(CityVO.class, map2, "city");
        if(city == null) {
            city = new CityVO();
        }
        city.setIds(new String[] {"v1","v3","v7", "v9"});
        city.setLabel("level");
        city.setCityId(5L);
        map2.put("city", KutaBeanUtil.bean2MapByJackson(city));

        String[] newArr = new String[] {"kim", "kuta", "john"};
        map2.put("author",newArr);

        nacosService.updateYamlConfig(map, "edit_test.yaml", "test");
    }
}