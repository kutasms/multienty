package com.chia.multienty.core.mybatis.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.CustomFileConditionWrapper;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.mybatis.KutaBaseMapper;
import com.chia.multienty.core.mybatis.generator.vue.VueCodeGeneratorProperties;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.sharding.algorithm.config.AlgorithmProvidedShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
//@ConditionalOnProperty(prefix = "spring.code-generator", name = "enabled", havingValue = "true")
@Slf4j
public class CodeAutoGenerator {
    enum FileType {
        MAPPER,
        ENTITY,
        DTO,
        SERVICE,
        CONTROLLER,
        PARAM_DETAIL_GET,
        PARAM_DELETE,
        PARAM_PAGE_GET,
        PARAM_SAVE,
        PARAM_UPDATE,
        PARAM_ENABLE,
        PARAM_DISABLE

    }
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
    private FreemarkerTemplateEngine engine = new FreemarkerTemplateEngine();
    @Autowired
    private VueCodeGeneratorProperties vueCodeGeneratorProperties;

    public void generate() {

        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&characterEncoding=utf8",
                generatorProperties.getDatabase().getHost(),
                generatorProperties.getDatabase().getPort(),
                generatorProperties.getDatabase().getDbName());

        String rootDir = generatorProperties.getRootDir() != null ? generatorProperties.getRootDir() : projectPath;

        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(url, generatorProperties.getDatabase().getUsername(),
                        generatorProperties.getDatabase().getPassword())
                .globalConfig(builder -> {
                    builder.outputDir(rootDir + "/" + generatorProperties.getCommonModuleName() + JAVA_PATH)
                            .enableSwagger()
                            .disableOpenDir()
                            .author(generatorProperties.getAuthor())
                    ;
                })


                .packageConfig(builder -> {
                    Map<OutputFile, String> map = new HashMap<>();
                    map.put(OutputFile.xml, rootDir + "/" + generatorProperties.getCommonModuleName() + RESOURCES_PATH + "/mapper");

                    if(generatorProperties.getControllerFilePathSuffix() != null) {
                        map.put(OutputFile.controller, rootDir + "/"
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
                    Map<String, List<String>> overrideMapping = generatorProperties.getOverrideMapping();
                    builder.addInclude(generatorProperties.getTables())
                            .addTablePrefix(generatorProperties.getTablePrefix())
                    ;

                    builder.controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle()
                            .formatFileName(generatorProperties.getFormatter().getController());

                    if(overrideMapping != null && overrideMapping.containsKey(FileType.CONTROLLER.name())) {
                        builder.controllerBuilder().enableFileOverride();
                    }

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


                    String namespacePath = rootDir + SymbolEnum.SLASH.getCode()
                            + generatorProperties.getCommonModuleName()
                            + generatorProperties.getDtoFilePathSuffix();

                    builder.customFile(file -> {
                                file.packageName(generatorProperties.getPackages().getDto())
                                        .fileName("DTO.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.DTO.name())
                                        .templatePath(DTO_TEMPLATE_PATH)
                                        .build();
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.DTO.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file -> {

                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("DetailGetParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_DETAIL_GET.name())
                                        .templatePath(ENTITY_GET_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_DETAIL_GET.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("SaveParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_SAVE.name())
                                        .templatePath(ENTITY_SAVE_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_SAVE.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("UpdateParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_UPDATE.name())
                                        .templatePath(ENTITY_UPDATE_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_UPDATE.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("DeleteParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_DELETE.name())
                                        .templatePath(ENTITY_DELETE_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_DELETE.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("PageGetParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_PAGE_GET.name())
                                        .templatePath(ENTITY_PAGE_GET_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_PAGE_GET.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("EnableParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_ENABLE.name())
                                        .templatePath(ENTITY_ENABLE_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_ENABLE.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customFile(file-> {
                                file.packageName(generatorProperties.getPackages().getParameter())
                                        .fileName("DisableParameter.java")
                                        .filePath(namespacePath)
                                        .fileType(FileType.PARAM_DISABLE.name())
                                        .templatePath(ENTITY_DISABLE_PARAMETER_PATH)
                                        .build()
                                ;
                                if(generatorProperties.overrideMapping != null
                                        && generatorProperties.overrideMapping.containsKey(FileType.PARAM_DISABLE.name())) {
                                    file.enableFileOverride();
                                }
                            })
                            .customMap(map)
                            .beforeOutputFile((tableInfo, objectMap) -> {
                                log.info("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                                InjectionConfig injectionConfig = ((ConfigBuilder)objectMap.get("config")).getInjectionConfig();
                                overrideParameterData(tableInfo, objectMap, injectionConfig);
                            })
                            .customFileCondition(
                                    (tableInfo, wrapper) -> {
                                        if(wrapper.getFile().isFileOverride()) {
                                            if(!checkOverrideCondition(tableInfo, wrapper)) {
                                                return false;
                                            }
                                        }
                                        if(wrapper.getFile().getFileType() != null
                                                && (wrapper.getFile().getFileType().equals(FileType.PARAM_ENABLE.name())
                                                    || wrapper.getFile().getFileType().equals(FileType.PARAM_DISABLE.name()))
                                        ) {
                                           return tableInfo.getFields().stream().anyMatch(p->p.getPropertyName().equals("status"));
                                        }
                                        return true;
                                    }
                            )
                    ;
                }).templateEngine(engine);

        fastAutoGenerator.execute();
    }

    private boolean checkOverrideCondition(TableInfo tableInfo, CustomFileConditionWrapper wrapper) {
        if(generatorProperties.getOverrideMapping() == null) {
            return false;
        }
        // 判定文件类型是否重写
        boolean fileTypeOverride = generatorProperties.getOverrideMapping().containsKey(wrapper.getFile().getFileType());
        if(fileTypeOverride) {
            boolean entityOverride = generatorProperties.getOverrideMapping()
                    .get(wrapper.getFile().getFileType()).contains(tableInfo.getEntityName());
            if(!entityOverride) {
                // 当此文件类型配置为重写，但当前类型不重写时先判定文件是否存在，如果文件已存在则过滤掉，不允许重写。
                String parentPath = engine.getConfigBuilder().getPathInfo().get(OutputFile.parent);
                String filePath = StringUtils.isNotBlank(wrapper.getFile().getFilePath()) ? wrapper.getFile().getFilePath()
                        : parentPath;
                if (StringUtils.isNotBlank(wrapper.getFile().getPackageName())) {
                    filePath = filePath + File.separator + wrapper.getFile().getPackageName().replaceAll("\\.",
                            StringPool.BACK_SLASH + File.separator);
                }
                Function<TableInfo, String> formatNameFunction = wrapper.getFile().getFormatNameFunction();
                String fileName = filePath
                        + File.separator + (null != formatNameFunction ? formatNameFunction.apply(tableInfo)
                        : tableInfo.getEntityName()) + wrapper.getFile().getFileName();
                File file = new File(fileName);
                if(file.exists()) {
                    // 文件存在
                    return false;
                }
            }
        }
        return true;
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
