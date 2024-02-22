package com.chia.multienty.core.mybatis.generator.vue;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.CustomFileConditionWrapper;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.mybatis.generator.GeneratorProperties;
import com.chia.multienty.core.tools.MapBuilder;
import com.chia.multienty.core.util.StringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(prefix = "spring.kuta.vue-code-generator", name="enabled", havingValue = "true")
@Slf4j
public class VueCodeAutoGenerator {

    enum FileType {
        COMPONENT,
        INDEX,
        EDITOR,
        APIS
    }
    enum ComponentType {
        SELECT,
        DIALOG,
        POPOVER
    }
    private final static String INDEX_PAGE_TEMPLATE = "/templates/vue/index.vue.ftl";
    private final static String EDITOR_PAGE_TEMPLATE = "/templates/vue/editor.vue.ftl";

    private final static String COMPONENT_SELECT_TEMPLATE = "/templates/vue/component-select.vue.ftl";
    private final static String COMPONENT_DIALOG_TEMPLATE = "/templates/vue/component-dialog.vue.ftl";
    private final static String COMPONENT_POPOVER_TEMPLATE = "/templates/vue/component-popover.vue.ftl";

    private final static String APIS_PAGE_TEMPLATE = "/templates/vue/apis.js.ftl";
    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private VueCodeGeneratorProperties vueProperties;

    private final static Map<String, String> componentTemplates;

    static {
        componentTemplates = new HashMap<>();
        componentTemplates.put(ComponentType.SELECT.name(), COMPONENT_SELECT_TEMPLATE);
        componentTemplates.put(ComponentType.DIALOG.name(), COMPONENT_DIALOG_TEMPLATE);
        componentTemplates.put(ComponentType.POPOVER.name(), COMPONENT_POPOVER_TEMPLATE);
    }

    public void generate() {
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&characterEncoding=utf8",
                generatorProperties.getDatabase().getHost(),
                generatorProperties.getDatabase().getPort(),
                generatorProperties.getDatabase().getDbName());
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(url, generatorProperties.getDatabase().getUsername(),
                        generatorProperties.getDatabase().getPassword())
                .globalConfig(builder -> {
                    builder.outputDir(vueProperties.getProjectPath())
                            .disableOpenDir()
                            .author(generatorProperties.getAuthor())

                    ;
                })
                .packageConfig(builder -> {

                })
                .strategyConfig(builder -> {
                    builder.addInclude(generatorProperties.getTables())
                            .addTablePrefix(generatorProperties.getTablePrefix())
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

                })
                .templateConfig(builder -> {
                    builder
                            .disable(TemplateType.CONTROLLER)
                            .disable(TemplateType.SERVICE_IMPL)
                            .disable(TemplateType.SERVICE)
                            .disable(TemplateType.MAPPER)
                            .disable(TemplateType.XML)
                            .disable(TemplateType.ENTITY);
                    ;
                })

                .injectionConfig(builder -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("package_dto", generatorProperties.getDtoFullPackageName());

                    Map<String, String> customFileMap = new HashMap<>();

                    builder
                            .customFile(file-> {
                                file.packageName(Strings.EMPTY)
                                        .fileName(".vue")
                                        .fileType(FileType.INDEX.name())
                                        .formatNameFunction(tableInfo -> StringUtil.camelCase(tableInfo.getEntityName()))
                                        .filePath(vueProperties.getProjectPath() + SymbolEnum.SLASH.getCode() + vueProperties.getViews().getPath())
                                        .templatePath(INDEX_PAGE_TEMPLATE)
                                        .build()
                                ;
                            })
                            .customFile(file->{
                                file.packageName(Strings.EMPTY)
                                        .fileName(".vue")
                                        .fileType(FileType.EDITOR.name())
                                        .formatNameFunction(tableInfo -> StringUtil.camelCase(tableInfo.getEntityName()) + "Editor")
                                        .filePath(vueProperties.getProjectPath() + SymbolEnum.SLASH.getCode() + vueProperties.getViews().getPath() + "/components")
                                        .templatePath(EDITOR_PAGE_TEMPLATE)
                                        .build();
                            })
                            .customFile(file-> {
                                file.packageName(Strings.EMPTY)
                                        .fileName(".js")
                                        .fileType(FileType.APIS.name())
                                        .formatNameFunction(tableInfo -> StringUtil.camelCase(tableInfo.getEntityName()))
                                        .filePath(vueProperties.getProjectPath() + SymbolEnum.SLASH.getCode() + vueProperties.getApis().getPath())
                                        .templatePath(APIS_PAGE_TEMPLATE)
                                ;
                            })

//                            .customFile(file-> {
//                                file.packageName(Strings.EMPTY)
//                                        .fileName(".vue")
//                                        .fileType(FileType.COMPONENT.name())
//                                        .formatNameFunction(tableInfo -> {
//                                                for (Map.Entry<String, VueGeneratorComponentProperties> entry :
//                                                        vueProperties.getComponents().getMapping().entrySet()) {
//                                                    if (entry.getValue().getEntity().equals(tableInfo.getEntityName())) {
//                                                        return entry.getKey();
//                                                    }
//                                                }
//                                                return tableInfo.getEntityName();
//                                            }
//                                        )
//                                        .filePath(vueProperties.getProjectPath() + SymbolEnum.SLASH.getCode() + vueProperties.getComponents().getPath())
//                                        .templatePath(COMPONENT_SELECT_TEMPLATE)
//                                ;
//                            })
                            .customMap(map)
                            .beforeOutputFile((tableInfo, objectMap) -> {
                                InjectionConfig injectionConfig = ((ConfigBuilder)objectMap.get("config")).getInjectionConfig();
                                overrideParameterData(tableInfo, objectMap, injectionConfig);
                            })
                            .customFileCondition((tableInfo, customFileConditionWrapper) -> filterComponent(tableInfo, customFileConditionWrapper));
                }).templateEngine(new FreemarkerTemplateEngine());

        fastAutoGenerator.execute();
    }

    private Boolean filterComponent(TableInfo tableInfo, CustomFileConditionWrapper wrapper) {
        FileType fileType = FileType.valueOf(wrapper.getFile().getFileType());
        Boolean result = true;

        switch (fileType) {
            case INDEX:
                break;
            case APIS:
                break;
            case COMPONENT:
                // 寻找符合条件的组件 实体名称相同 && 组件模版相同
                Optional<VueGeneratorComponentProperties> any = vueProperties.getComponents().getMapping().values()
                        .stream()
                        .filter(p ->
                                p.getEntity().equals(tableInfo.getEntityName())
                        )
                        .findAny();
                result = any.isPresent();
                break;
//            case EDITOR:
//                if(vueProperties.getViews().getPages().containsKey(tableInfo.getEntityName())) {
//                    result = vueProperties.getViews().getPages().get(tableInfo.getEntityName()).getEditor() != null;
//                } else {
//                    result = false;
//                }
//                break;
        }
        return result;
    }

    @SneakyThrows
    private void rewriteComponentTemplatePath(TableInfo tableInfo, InjectionConfig injectionConfig) {
        for (CustomFile customFile : injectionConfig.getCustomFiles()) {
            if(customFile.getFileType().equals(FileType.COMPONENT.name())) {
                Optional<VueGeneratorComponentProperties> any = vueProperties
                        .getComponents()
                        .getMapping()
                        .values()
                        .stream()
                        .filter(p -> p.getEntity().equals(tableInfo.getEntityName()))
                        .findAny();
                if(any.isPresent()) {
                    Field field = customFile.getClass().getDeclaredField("templatePath");
                    field.setAccessible(true);
                    field.set(customFile, componentTemplates.get(
                                    vueProperties
                                            .getComponents()
                                            .getMapping()
                                            .values()
                                            .stream()
                                            .filter(p->p.getEntity().equals(tableInfo.getEntityName()))
                                            .findAny()
                                            .get()
                                            .getType()
                            )
                    );
                    field.setAccessible(false);
                }
            }
        }
    }

    @SneakyThrows
    private void overrideParameterData(TableInfo tableInfo, Map<String,Object> objectMap, InjectionConfig injectionConfig){
        boolean generateApis = vueProperties.getApis().getEnabled();
        boolean generateViews = vueProperties.getViews().getEnabled();

        objectMap.put("generateApis", generateApis);
        objectMap.put("components", vueProperties.getComponents());
        objectMap.put("inputIconMapping", vueProperties.getInputIconMapping());
        objectMap.put("formatter", vueProperties.getViews().getFormatter());
        objectMap.put("apis", vueProperties.getApis());

        for (CustomFile customFile : injectionConfig.getCustomFiles()) {
            if(vueProperties.getViews().getPathOverride().containsKey(tableInfo.getEntityName())) {
                if(customFile.getFileType().equals(FileType.INDEX.name()) || customFile.getFileType().equals(FileType.APIS.name())) {
                    Field fieldPackageName = customFile.getClass().getDeclaredField("packageName");
                    fieldPackageName.setAccessible(true);
                    fieldPackageName.set(customFile, vueProperties
                            .getViews().getPathOverride()
                            .get(tableInfo.getEntityName()));
                    fieldPackageName.setAccessible(false);
                }
                else if (customFile.getFileType().equals(FileType.EDITOR.name())) {
                    Field fieldFilePath = customFile.getClass().getDeclaredField("filePath");
                    fieldFilePath.setAccessible(true);
                    String viewsPath = vueProperties.getProjectPath() + SymbolEnum.SLASH.getCode() + vueProperties.getViews().getPath();
                    fieldFilePath.set(customFile, viewsPath
                            + SymbolEnum.SLASH.getCode()
                            + vueProperties
                            .getViews().getPathOverride()
                            .get(tableInfo.getEntityName())
                            + SymbolEnum.SLASH.getCode()
                            + "components"
                    );
                    fieldFilePath.setAccessible(false);
                }
            }
        }
        // 重写组件的模版路径（根据组件类型进行区分）
        rewriteComponentTemplatePath(tableInfo, injectionConfig);

        if(generateViews) {
            VueGeneratorViewPageProperties viewPageProperties = null;
            if(vueProperties.getViews().getPages().containsKey(tableInfo.getEntityName())) {
                viewPageProperties = vueProperties.getViews().getPages().get(tableInfo.getEntityName());
            } else {
                viewPageProperties = new VueGeneratorViewPageProperties();
                viewPageProperties.setIndex(new VueGeneratorPageIndexProperties());
                viewPageProperties.getIndex().setTable(new VueGeneratorPageIndexTableProperties());
                if(viewPageProperties.getEditor() == null) {
                    viewPageProperties.setEditor(new VueGeneratorEditorProperties());
                    viewPageProperties.getEditor().setFormItems(getDefaultFormItemMap(tableInfo));
                }
                if(viewPageProperties.getIndex().getTable().getTableColumns() == null) {
                    String[] columns = new String[1];
                    viewPageProperties.getIndex().getTable().setTableColumns(
                            tableInfo
                                    .getFields()
                                    .stream()
                                    .map(m->m.getPropertyName())
                                    .collect(Collectors.toList())
                                    .toArray(columns));
                }
            }
            objectMap.put("view", viewPageProperties);
        }
    }

    private Map<String, VueGeneratorEditorFormItemProperties> getDefaultFormItemMap(TableInfo tableInfo) {
        Map<String, VueGeneratorEditorFormItemProperties> formItemMap = new HashMap<>();
        tableInfo.getFields().forEach(field-> {
            if(!field.isKeyFlag()
                    && !field.isVersionField()
                    && !field.isLogicDeleteField()
                    && !field.getPropertyName().equals("createTime")
                    && !field.getPropertyName().equals("updateTime")
                    && !field.getPropertyName().equals("status")) {
                VueGeneratorEditorFormItemProperties formItemProperties = null;
                IColumnType columnType = field.getColumnType();
                String type = columnType.getType();
                if(type.equals(DbColumnType.BASE_BOOLEAN.getType())
                    || type.equals(DbColumnType.BOOLEAN.getType())) {
                     formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("el-checkbox");
                } else if(type.equals(DbColumnType.LOCAL_TIME.getType())) {
                     formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("el-time-select");
                } else if (type.equals(DbColumnType.LOCAL_DATE.getType())) {
                    formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("el-date-picker").setType("date");
                } else if(type.equals(DbColumnType.LOCAL_DATE_TIME.getType())) {
                     formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("el-date-picker").setType("datetime");
                } else {
                     formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("el-input");
                }
                formItemProperties.setBindings(MapBuilder.<String,String>create()
                        .add("v-model", "form." + field.getPropertyName()).get());

                // 重写value等属性专用组件
                if(field.getPropertyName().equals("valueType")) {
                    formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("kt-value-type-selector")
                            .setBindings(MapBuilder.<String, String>create().add("v-model","form.valueType").get());
                }
                if(field.getPropertyName().equals("value")) {
                    formItemProperties = new VueGeneratorEditorFormItemProperties()
                            .setComponent("kt-value-input")
                            .setBindings(MapBuilder.<String, String>create()
                                    .add("v-model","form.value")
                                    .add("value-type", type)
                                    .get());
                }

                if(field.getPropertyName().equals("remark")) {
                    formItemProperties.setType("textarea");
                }

                formItemMap.put(field.getPropertyName(), formItemProperties);
            }
            if(field.isKeyFlag()
                    || field.isVersionField()
                    || field.getPropertyName().equals("createTime")
                    || field.getPropertyName().equals("updateTime")
                    || field.getPropertyName().equals("status")) {
                VueGeneratorEditorFormItemProperties formItemProperties = new VueGeneratorEditorFormItemProperties()
                        .setComponent("span").setReadOnly(true);
                formItemMap.put(field.getPropertyName(), formItemProperties);
            }
        });
        return formItemMap;
    }
}
