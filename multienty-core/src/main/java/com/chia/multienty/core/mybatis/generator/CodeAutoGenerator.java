package com.chia.multienty.core.mybatis.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.mybatis.KutaBaseMapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.util.StringUtil;
import com.chia.multienty.core.mybatis.generator.vue.VueCodeGeneratorProperties;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.sharding.algorithm.config.AlgorithmProvidedShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CodeAutoGenerator {
    private static String ENTITY_TEMPLATE_PATH = "/templates/entity.java";
    private static String XML_TEMPLATE_PATH = "/templates/mapper.xml";

    private static  String MAPPER_TEMPLATE_PATH = "/templates/mapper.java";
    private static  String SERVICE_TEMPLATE_PATH = "/templates/service.java";
    private static  String SERVICE_IMPL_TEMPLATE_PATH = "/templates/serviceImpl.java";
    private static  String DTO_TEMPLATE_PATH = "/templates/dto.java.ftl";
    private static  String CONTROL_TEMPLATE_PATH = "/templates/controller.java";

    private static String ENTITY_GET_PARAMETER_PATH = "/templates/parameter/entity_get_parameter.java.ftl";

    private static String ENTITY_SAVE_PARAMETER_PATH = "/templates/parameter/entity_save_parameter.java.ftl";
    private static String ENTITY_UPDATE_PARAMETER_PATH = "/templates/parameter/entity_update_parameter.java.ftl";

    private static String ENTITY_DELETE_PARAMETER_PATH = "/templates/parameter/entity_delete_parameter.java.ftl";

    private static String ENTITY_PAGE_GET_PARAMETER_PATH = "/templates/parameter/entity_page_get_parameter.java.ftl";
    private static String ENTITY_ENABLE_PARAMETER_PATH = "/templates/parameter/entity_enable_parameter.java.ftl";

    private static String ENTITY_DISABLE_PARAMETER_PATH =  "/templates/parameter/entity_disable_parameter.java.ftl";
    private static String projectPath = System.getProperty("user.dir");

    private final static String JAVA_PATH = "/src/main/java";
    private final static String RESOURCES_PATH = "/src/main/resources";

    @Autowired
    private Optional<RuleConfiguration> ruleConfiguration;
    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private VueCodeGeneratorProperties vueCodeGeneratorProperties;

    public void generate() {
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&characterEncoding=utf8",
                generatorProperties.getDatabase().getHost(),
                generatorProperties.getDatabase().getPort(),
                generatorProperties.getDatabase().getDbName());
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(url, generatorProperties.getDatabase().getUsername(),
                        generatorProperties.getDatabase().getPassword())
                .globalConfig(builder -> {
                    builder.outputDir(projectPath + "/" + generatorProperties.getCommonModuleName() + JAVA_PATH)
                            .enableSwagger()
                            .disableOpenDir()
                            .author(generatorProperties.getAuthor())
                    ;
                })
                .packageConfig(builder -> {
                    Map<OutputFile, String> map = new HashMap<>();
                    map.put(OutputFile.xml, projectPath + "/" + generatorProperties.getCommonModuleName() + RESOURCES_PATH + "/mapper");

                    if(generatorProperties.getControllerFilePathSuffix() != null) {
                        map.put(OutputFile.controller, projectPath + "/"
                                + generatorProperties.getControllerModuleName()
                                + generatorProperties.getControllerFilePathSuffix());
                    }
                    builder.parent(generatorProperties.getRootPackage())
                            .controller(generatorProperties.getPackages().getController())
                            .entity(generatorProperties.getPackages().getEntity())
                            .service(generatorProperties.getPackages().getService())
                            .mapper(generatorProperties.getPackages().getMapper())
                            .serviceImpl(generatorProperties.getPackages().getServiceImpl())
                            .pathInfo(map)
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude(generatorProperties.getTables())
                            .addTablePrefix(generatorProperties.getTablePrefix())
                    ;

                    builder.controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle()
                            .formatFileName(generatorProperties.getFormatter().getController());

                    builder.serviceBuilder()
                            .superServiceClass(KutaBaseService.class)
                            .superServiceImplClass(KutaBaseServiceImpl.class)
                            .formatServiceFileName(generatorProperties.getFormatter().getService())
                            .formatServiceImplFileName(generatorProperties.getFormatter().getServiceImpl())

                    ;
                    List<IFill> columns = new ArrayList<>();
                    generatorProperties.getTableFills().forEach((k, v) -> {
                        switch (v) {
                            case "INSERT":
                                columns.add(new Column(k, FieldFill.INSERT));
                                break;
                            case "UPDATE":
                                columns.add(new Column(k, FieldFill.UPDATE));
                                break;
                            case "DEFAULT":
                                columns.add(new Column(k, FieldFill.DEFAULT));
                                break;
                            case "INSERT_UPDATE":
                                columns.add(new Column(k, FieldFill.INSERT_UPDATE));
                                break;
                        }
                    });

                    builder.entityBuilder().disableSerialVersionUID()
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok()
                            .idType(IdType.INPUT)
                            .logicDeleteColumnName(generatorProperties.getLogicDeleteColumnName())
                            .enableTableFieldAnnotation()
                            .enableFileOverride()
                            .enableChainModel()
                    ;

                    if(generatorProperties.getSuperEntityClassName() != null) {
                        builder.entityBuilder().superClass(generatorProperties.getSuperEntityClassName());
                    }
                    if(generatorProperties.getSuperEntityColumns() != null && generatorProperties.getSuperEntityColumns().length > 0) {
                        builder.entityBuilder().addSuperEntityColumns(generatorProperties.getSuperEntityColumns());
                    }

                    if (columns.size() > 0) {
                        builder.entityBuilder().addTableFills(columns);
                    }

                    builder.mapperBuilder().superClass(KutaBaseMapper.class)
                            .formatMapperFileName(generatorProperties.getFormatter().getMapper())
                            .formatXmlFileName(generatorProperties.getFormatter().getXml())
                    ;
                })
                .templateConfig(builder -> {
                    builder.entity(ENTITY_TEMPLATE_PATH)
                            .service(SERVICE_TEMPLATE_PATH)
                            .serviceImpl(SERVICE_IMPL_TEMPLATE_PATH)
                            .xml(XML_TEMPLATE_PATH)
                            .mapper(MAPPER_TEMPLATE_PATH)
                            .controller(CONTROL_TEMPLATE_PATH)
                    ;
                })

                .injectionConfig(builder -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("package_dto", generatorProperties.getDtoFullPackageName());

                    Map<String, String> customFileMap = new HashMap<>();

                    String namespacePath = projectPath + SymbolEnum.SLASH.getCode()
                            + generatorProperties.getCommonModuleName()
                            + generatorProperties.getDtoFilePathSuffix();

                    builder.customFile(file -> {
                                file.packageName(generatorProperties.getPackages().getDto())
                                        .fileName("DTO.java")
                                        .filePath(namespacePath)
                                        .templatePath(DTO_TEMPLATE_PATH)
                                        .build();
                            })
                            .customFile(file -> {

                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("DetailGetParameter.java")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_GET_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("SaveParameter.java")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_SAVE_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("UpdateParameter.java")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_UPDATE_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("DeleteParameter.java")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_DELETE_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("PageGetParameter.java")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_PAGE_GET_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("EnableParameter.java")
                                        .fileType("PARAMETER_ENABLE")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_ENABLE_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("DisableParameter.java")
                                        .fileType("PARAMETER_DISABLE")
                                        .filePath(namespacePath)
                                        .templatePath(ENTITY_DISABLE_PARAMETER_PATH)
                                        .build()
                                ;
                            })
                            .customMap(map)
                            .beforeOutputFile((tableInfo, objectMap) -> {
                                List<TableField> fields = tableInfo.getFields();
                                log.info("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                                InjectionConfig injectionConfig = ((ConfigBuilder)objectMap.get("config")).getInjectionConfig();
                                overrideParameterData(tableInfo, objectMap, injectionConfig);
                            })
                            .customFileCondition(
                                    (tableInfo, wrapper) -> {
                                        if(wrapper.getFile().getFileType() != null
                                                && (wrapper.getFile().getFileType().equals("PARAMETER_ENABLE")
                                                    || wrapper.getFile().getFileType().equals("PARAMETER_DISABLE"))
                                        ) {
                                           return tableInfo.getFields().stream().anyMatch(p->p.getPropertyName().equals("status"));
                                        }
                                        return true;
                                    }
                            )
                    ;
                }).templateEngine(new FreemarkerTemplateEngine());

        fastAutoGenerator.execute();
    }

    private void overrideParameterData(TableInfo tableInfo, Map<String,Object> objectMap, InjectionConfig injectionConfig) {

        for (CustomFile customFile : injectionConfig.getCustomFiles()) {
            if(customFile.getPackageName()!=null &&
                    customFile.getPackageName().startsWith(generatorProperties.getPackages().getParameter())) {
                try {
                    Field fieldPackageName = customFile.getClass().getDeclaredField("packageName");
                    fieldPackageName.setAccessible(true);
                    fieldPackageName.set(customFile, generatorProperties.getPackages().getParameter()
                            + SymbolEnum.DOT.getCode()
                            + StringUtil.camelCase(
                            generatorProperties.getPackageMergeMapping().containsKey(tableInfo.getEntityName())
                                    ? generatorProperties.getPackageMergeMapping().get(tableInfo.getEntityName())
                                    : tableInfo.getEntityName()
                    ));
                    fieldPackageName.setAccessible(false);

                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String parameterPackageName = generatorProperties.getRootPackage()
                + SymbolEnum.DOT.getCode()
                + generatorProperties.getPackages().getParameter()
                + SymbolEnum.DOT.getCode()
                + StringUtil.camelCase(
                generatorProperties.getPackageMergeMapping().containsKey(tableInfo.getEntityName())
                        ? generatorProperties.getPackageMergeMapping().get(tableInfo.getEntityName())
                        : tableInfo.getEntityName()
        );
        ruleConfiguration.ifPresent(ruleConfig-> {
            AlgorithmProvidedShardingRuleConfiguration shardingConfig = (AlgorithmProvidedShardingRuleConfiguration) ruleConfig;
            Optional<ShardingTableRuleConfiguration> optional = shardingConfig
                    .getTables()
                    .stream()
                    .filter(p -> p.getLogicTable().equals(tableInfo.getName()))
                    .findAny();
            if(optional.isPresent()) {
                ShardingInfo shardingInfo = new ShardingInfo();
                if(optional.get().getDatabaseShardingStrategy() == null) {
                    ShardingStrategyConfiguration defaultDatabaseStrategyConfig = shardingConfig.getDefaultDatabaseShardingStrategy();
                    if(defaultDatabaseStrategyConfig != null && defaultDatabaseStrategyConfig.getType().equals("STANDARD")) {
                        StandardShardingStrategyConfiguration dbStandardStrategyConfig = (StandardShardingStrategyConfiguration) defaultDatabaseStrategyConfig;
                        shardingInfo.setDatabaseShardingColumnName(StringUtil.toCamelCase(dbStandardStrategyConfig.getShardingColumn()));
                    }
                } else {
                    ShardingStrategyConfiguration databaseShardingStrategy = optional.get().getDatabaseShardingStrategy();
                    if(databaseShardingStrategy != null && databaseShardingStrategy.getType().equals("STANDARD")) {
                        StandardShardingStrategyConfiguration dbStandardStrategyConfig = (StandardShardingStrategyConfiguration) databaseShardingStrategy;
                        shardingInfo.setDatabaseShardingColumnName(StringUtil.toCamelCase(dbStandardStrategyConfig.getShardingColumn()));
                    }
                }

                shardingInfo.setShardingDatabase(shardingInfo.getDatabaseShardingColumnName() != null);

                ShardingStrategyConfiguration ssc = optional.get().getTableShardingStrategy();
                if(ssc == null) {
                   ssc = shardingConfig.getDefaultTableShardingStrategy();
                }
                if(ssc != null) {
                    String shardingType = optional.get().getTableShardingStrategy().getType();
                    if(shardingType.equals("STANDARD")) {
                        StandardShardingStrategyConfiguration tableShardingStrategy =
                                (StandardShardingStrategyConfiguration) optional.get().getTableShardingStrategy();
                        if(tableShardingStrategy != null) {
                            shardingInfo.setTableShardingColumnName(StringUtil.toCamelCase(tableShardingStrategy.getShardingColumn()));
                            shardingInfo.setShardingTable(true);
                        } else {
                            shardingInfo.setShardingTable(false);
                        }
                    }
                }
                if(shardingInfo.getShardingDatabase() || shardingInfo.getShardingTable()) {
                    injectionConfig.getCustomMap().put("sharding", shardingInfo);
                }
            } else {
                injectionConfig.getCustomMap().remove("sharding");
            }
        });

        injectionConfig.getCustomMap().put("package_parameter", parameterPackageName);
    }
}
