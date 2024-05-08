package com.chia.multienty.core;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;
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
        String yamlStr = "spring:\n" +
                "  vue-code-generator:\n" +
                "    enabled: true\n" +
                "    project-path: C:\\projects\\multienty\\multienty-vue-platform\\src\n" +
                "    ignoreEntities:\n" +
                "      - BalanceBill\n" +
                "      - TenantSubAccountRole\n" +
                "    override-mapping:\n" +
                "      EDITOR:\n" +
                "        - TenantSubAccount\n" +
                "    serviceModuleMapping:\n" +
                "      Notice: master\n" +
                "      JsonConfig: master\n" +
                "      Setting: master\n" +
                "      ChineseCity: master\n" +
                "      Label: master\n" +
                "      RabbitLog: master\n" +
                "      UploadFile: master\n" +
                "      Category: storage\n" +
                "      CategorySkuProp: storage\n" +
                "      CategorySkuPropValue: storage\n" +
                "      Product: storage\n" +
                "      ProductCase: storage\n" +
                "      ProductCaseMedia: storage\n" +
                "      ProductCert: storage\n" +
                "      ProductMedia: storage\n" +
                "      ProductSku: storage\n" +
                "      SkuAttribute: storage\n" +
                "      Customer: user\n" +
                "      CustomerWx: user\n" +
                "      CustomerAddress: user\n" +
                "      CustomerBalance: user\n" +
                "      CustomerBill: user\n" +
                "      BalanceBill: user\n" +
                "      Tenant: user\n" +
                "      TenantSecret: user\n" +
                "      App: master\n" +
                "      AppInstance: user\n" +
                "      SecretAuth: user\n" +
                "    apis:\n" +
                "      enabled: true\n" +
                "      path: api\n" +
                "    components:\n" +
                "      path: components\n" +
                "      mapping:\n" +
                "        tenant-selector:\n" +
                "          type: DIALOG\n" +
                "          entity: Tenant\n" +
                "          key: tenantId\n" +
                "          label: name\n" +
                "          multiple: false\n" +
                "          path: user/tenantSelector\n" +
                "          bindings:\n" +
                "            v-model:\n" +
                "              selectedTenant: Object\n" +
                "            \"visible.sync\":\n" +
                "              tenantSelectorVisible: Boolean\n" +
                "    input-icon-mapping:\n" +
                "      label: kt-icon-name-fill\n" +
                "      alias: kt-icon-name-fill\n" +
                "    path-override:\n" +
                "      Notice: infra\n" +
                "      JsonConfig: system\n" +
                "      Setting: system\n" +
                "      ChineseCity: infra\n" +
                "      Label: infra\n" +
                "      RabbitLog: infra\n" +
                "      UploadedFile: infra\n" +
                "      Category: storage\n" +
                "      CategorySkuProp: storage\n" +
                "      CategorySkuPropValue: storage\n" +
                "      Product: storage\n" +
                "      ProductCase: storage\n" +
                "      ProductCaseMedia: storage\n" +
                "      ProductCert: storage\n" +
                "      ProductMedia: storage\n" +
                "      ProductSku: storage\n" +
                "      SkuAttribute: storage\n" +
                "      Customer: user\n" +
                "      CustomerWx: user\n" +
                "      CustomerAddress: user\n" +
                "      CustomerBalance: user\n" +
                "      CustomerBill: user\n" +
                "      BalanceBill: user\n" +
                "      Tenant: user\n" +
                "      TenantRole: user\n" +
                "      TenantSubAccount: user\n" +
                "      TenantSecret: user\n" +
                "      App: infra\n" +
                "      AppInstance: infra\n" +
                "      SecretAuth: user\n" +
                "    views:\n" +
                "      enabled: true\n" +
                "      path: views\n" +
                "      formatter:\n" +
                "        valueType: valueType2CN\n" +
                "        status: status2CN\n" +
                "      pages:\n" +
                "        ChineseCity:\n" +
                "          index:\n" +
                "            search-input-items:\n" +
                "              - keywords\n" +
                "            keywordsPlaceHolder: 请输入城市名称\n" +
                "            selectable-status: '[-1, 3, 8]'\n" +
                "            table:\n" +
                "              paging:\n" +
                "                layout: prev, pager, next\n" +
                "                pager-count: 11\n" +
                "              table-columns:\n" +
                "                - cityId\n" +
                "                - cityName\n" +
                "                - createTime\n" +
                "                - updateTime\n" +
                "              table-item-actions:\n" +
                "                - edit\n" +
                "                - delete\n" +
                "                - web-log\n" +
                "          editor:\n" +
                "            props:\n" +
                "              pid: cityPid\n" +
                "            size:\n" +
                "              width: 500px\n" +
                "              height: 50%\n" +
                "              top: 25%\n" +
                "            form-items:\n" +
                "              label:\n" +
                "                component: el-input\n" +
                "              alias:\n" +
                "                component: el-input\n" +
                "              valueType:\n" +
                "                component: value-type-selector\n" +
                "                name: valueTypeSelector\n" +
                "                path: views/system/components/valueTypeSelector\n" +
                "              value:\n" +
                "                component: kt-value-selector\n" +
                "                bindings:\n" +
                "                  v-model: form.value\n" +
                "                  value-type: form.valueType\n" +
                "              deletable:\n" +
                "                component: el-checkbox\n" +
                "              encrypted:\n" +
                "                component: el-checkbox\n" +
                "              remark:\n" +
                "                component: el-input\n" +
                "                type: textarea\n";
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(yamlStr, Map.class);
        log.info(JSONObject.toJSONString(map, true));
//        String pattern = String.format("^%s_\\d{6}$", "saas_order");
//        Pattern compile = Pattern.compile(pattern);
//        Matcher matcher = compile.matcher("saas_order_202502");
//        Assert.assertTrue(matcher.find());
//        String datetime = "2021-04-30T18:58:48+08:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        LocalDateTime formatted = LocalDateTime.parse(datetime, formatter);
//        log.info(TimeUtil.to19(formatted));
    }
}
