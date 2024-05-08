package com.chia.multienty.core.mybatis.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.code-generator")
@Data
@Component
public class GeneratorProperties {
    private String author = "Multi Tenant Auto Generator";
    private String rootPackage;
    private String rootDir;
    private String commonModuleName;
    private String tablePrefix;
    private String controllerModuleName;
    private String controllerFilePathSuffix;
    private String dtoFilePathSuffix;
    private String idType = "INPUT";
    private String logicDeleteColumnName;
    private String dtoFullPackageName;
    private String[] superEntityColumns;
    private Map<String, String> tableFills;
    private GeneratorPackageProperties packages;
    private GeneratorFormatterProperties formatter;
    private GeneratorDatabaseProperties database;
    private String[] tables;

    private Map<String, String> packageMergeMapping;

    private String superEntityClassName = null;
    public IdType getIdTypeEnum() {
        if(idType == null) {
            return null;
        }
        return IdType.valueOf(idType);
    }

    public Map<String, List<String>> overrideMapping;
}
