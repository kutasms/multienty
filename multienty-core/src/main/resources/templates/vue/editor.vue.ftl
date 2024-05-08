<template>
  <el-dialog
    :visible.sync="innerVisible"
    :title="title"
    :modal-append-to-body="true"
    :append-to-body="true"
    :close-on-click-modal="false"
    width="${view.editor.size.width}"
    <#if view.editor.size.top??>
    top="${view.editor.size.top}"
    </#if>
  >
    <div class="fill box scroll-auto" style="height: 70vh !important">
      <el-form
       ref="form"
       :model="form"
       :rules="formRules"
       class="wp100 pad-none">
       <div class="flex-row h-between v-start">
        <#list view.editor.formItems?keys as key>
          <#list table.fields as field>
            <#if field.fill??>
            <#elseif (versionFieldName!"") == field.name>
            <#elseif (logicDeleteFieldName!"") == field.name>
            <#elseif field.keyFlag>
            <#elseif field.propertyName == key>
              <#assign comp = view.editor.formItems[key]>
          <el-form-item label="${field.comment!}" prop="${field.propertyName}" class="half pad-l-20 pad-r-20">
              <#if comp.component == "el-input">
            <el-input
              v-if="mode !== 2"
              v-model="form.${key}"
              :disabled="mode === 2"
              placeholder="请输入${field.comment!}"
              prefix-icon="kt-icon-name-fill"
              maxlength="${field.metaInfo.length}"
              :show-word-limit="true"
              <#if comp.type??>
              type="textarea"
              </#if>
              class="wp100"
            ></el-input>
            <div v-else class="wp100 inline-block">
              <span>{{ form.${field.propertyName} }}</span>
            </div>
              <#elseif comp.component == "span">
            <div class="wp100 inline-block">
              <span>{{ form.${field.propertyName} }}</span>
            </div>
              <#elseif comp.component == "rich-text-editor">
            <div class="wp100 inline-block">
              <rich-text-editor v-if="mode !== 2" v-model="form.${field.propertyName}"></rich-text-editor>
              <span v-else>{{ form.${field.propertyName} }}</span>
            </div>
              <#elseif comp.component == "el-checkbox">
            <div class="wp100 inline-block">
              <el-checkbox v-if="mode !== 2" v-model="form.${field.propertyName}" :disabled="mode === 2">${field.comment!}</el-checkbox>
              <span v-else>{{ form.${field.propertyName} }}</span>
            </div>
              <#elseif comp.component == "el-radio-group">
            <div class="wp100 inline-block">
              <el-radio-group v-if="mode !== 2" v-model="form.${field.propertyName}" :disabled="mode === 2">
                <#list comp.options?keys as op>
                <el-radio-button :label="${comp.options[op]}">${op}</el-radio-button>
                </#list>
              </el-radio-group>
              <span v-else>{{ form.${field.propertyName} }}</span>
            </div>
              <#elseif comp.component == "el-time-select">
            <div class="wp100 inline-block">
              <el-time-select v-if="mode !== 2" v-model="form.${field.propertyName}" :disabled="mode === 2"></el-time-select>
              <span v-else>{{ form.${field.propertyName} }}</span>
            </div>
              <#elseif comp.component == "el-date-picker">
            <div class="wp100 inline-block">
              <el-date-picker
                v-if="mode !== 2"
                v-model="form.${field.propertyName}"
                type="${comp.type}"
                value-format="yyyy-MM-dd HH:mm:ss"
                :disabled="mode === 2"
              />
              <span v-else>{{ form.${field.propertyName} }}</span>
            </div>
              <#else>
            <div class="wp100 inline-block">
              <${comp.hyphenName!}
                v-if="mode !== 2"
                  <#if comp.bindings??>
                    <#list comp.bindings?keys as bindingKey>
                    <#if bindingKey == "v-model">
                ${bindingKey}="${comp.bindings[bindingKey]}"
                    <#else>
                :${bindingKey}="${comp.bindings[bindingKey]}"
                    </#if>
                  </#list>
                </#if>
              />
              <span v-else>{{ form.${field.propertyName} }}</span>
            </div>
              </#if>
          </el-form-item>

            </#if>
          </#list>
        </#list>
        </div>
      </el-form>
    </div>

    <div slot="footer">
      <el-button
        v-if="mode !== 2"
        type="text"
        class="margin-right-long"
        @click="innerVisible = false"
      >
        取消
      </el-button>
      <el-button v-if="[0, 1].includes(mode)" type="primary" @click="save">
        保存
      </el-button>
      <el-button v-else type="primary" @click="innerVisible = false">
        关闭
      </el-button>
    </div>
  </el-dialog>
</template>
<script>
  <#list table.fields as field>
    <#if field.propertyName == "status">
      <#assign statusExists = field.propertyName == "status">
    </#if>
  </#list>
  <#if statusExists??>
  import { save, update, enable, disable } from '@/${apis.path}/${pkg}/${entity?uncap_first}'
  <#else>
  import { save, update } from '@/api/${pkg}/${entity?uncap_first}'
  </#if>

  <#list view.editor.formItems?keys as key>
    <#if !view.editor.formItems[key].component?starts_with("el-") && !view.editor.formItems[key].component?starts_with("kt-") && view.editor.formItems[key].component != "span">
  import ${view.editor.formItems[key].component} from '@/components/${view.editor.formItems[key].path}'
    </#if>
  </#list>
  const emptyForm = {
<#list table.fields as field>
    ${field.propertyName}: null,
</#list>
  }
  export default {
    name: '${controllerMappingHyphen}-editor',
    components: {
  <#list view.editor.formItems?keys as key>
    <#if !view.editor.formItems[key].component?starts_with("el-") && !view.editor.formItems[key].component?starts_with("kt-") && view.editor.formItems[key].component != "span">
      ${view.editor.formItems[key].component},
    </#if>
  </#list>
    },
    props: {
      visible: {
        type: Boolean,
        default: false,
      },
      mode: {
        type: Number,
        default: 0,
      },
      value: {
        type: Object,
        default: null,
      },
    },
    data() {
      return {
        form: JSON.parse(JSON.stringify(emptyForm)),
        <#if view.editor.dataValues?? && (view.editor.dataValues?size > 0)>
            <#list view.editor.dataValues?keys as key>
        ${key}: ${view.editor.dataValues[key]},
            </#list>
        </#if>
        formRules: {
        <#list table.fields as field>
            <#if field.keyFlag>
            <#elseif field.fill??>
            <#else>
          ${field.propertyName}: [
                <#if field.metaInfo.nullable>
                <#else>
                    <#if field.columnType == "STRING">
                        <#assign actionType = '填写'>
                    <#else>
                        <#assign actionType = '选择'>
                    </#if>
            { required: true, message: '请${actionType}${field.comment!}', trigger: 'blur' }
                </#if>
          ],
            </#if>
        </#list>
        },
      }
    },
    computed: {
      title: function () {
        switch (this.mode) {
          case 0:
            return '添加${table.comment!}'
          case 1:
            return '编辑${table.comment!}'
          case 2:
            return '查看${table.comment!}'
          default:
            return '未知'
        }
      },
      innerVisible: {
        get() {
          return this.visible
        },
        set(val) {
          this.$emit('update:visible', val)
        },
      },
    },
    watch: {
      value: {
        handler(newVal) {
          if ([1, 2].includes(this.mode)) {
            var formatted = JSON.parse(JSON.stringify(newVal))
            if (newVal.cityId) {
              formatted.city = {
                ids: newVal.cityIds.split(',').map((m) => Number(m)),
                cityId: newVal.cityId,
                label: newVal.cityName,
              }
            }
            this.form = formatted
          } else {
            this.form = JSON.parse(JSON.stringify(emptyForm))
          }
        },
        immediate: true,
      },
      <#if view.editor.watches?? && (view.editor.watches?size > 0)>
        <#list view.editor.watches?keys as key>
      ${key}: {
        handler(newVal) {
          if(newVal) {
            this.form.${view.editor.watches[key]} = newVal.${view.editor.watches[key]}
          }
        },
      },
        </#list>
      </#if>
    },
    methods: {
      save() {
        // TODO: add logic code at here
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.mode === 0 ? this.insert() : this.update()
          }
        })
      },
      insert() {
        save(this.form).then((rsp) => {
          this.${'$'}notify({
            title: '成功',
            type: 'success',
            message: `${table.comment!}创建成功`,
          })
          this.${'$'}emit('success', rsp.data)
          this.innerVisible = false
        })
        .catch((e) => {
          console.log(e)
        })
      },
      update() {
        update(this.form).then((rsp) => {
          this.${'$'}notify({
            title: '成功',
            type: 'success',
            message: `${table.comment!}更新成功`,
          })
          this.${'$'}emit('success', rsp.data)
        })
        .catch((e) => {
          console.log(e)
        })
      },
      <#if statusExists??>
      enable() {
        enable(this.form).then((rsp) => {
          this.${'$'}notify({
            title: '成功',
            type: 'success',
            message: `${table.comment!}已启用`,
          })
          this.${'$'}emit('success', rsp.data)
        })
        .catch((e) => {
          console.log(e)
        })
      },
      disable() {
        disable(this.form).then((rsp) => {
          this.${'$'}notify({
            title: '成功',
            type: 'success',
            message: `${table.comment!}已禁用`,
          })
          this.${'$'}emit('success', rsp.data)
        })
        .catch((e) => {
          console.log(e)
        })
      },
      </#if>
    },
  }
</script>
<style lang="scss" scoped>
</style>
