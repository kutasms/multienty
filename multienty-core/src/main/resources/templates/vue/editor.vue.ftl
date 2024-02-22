<template>
  <el-dialog
    :visible.sync="innerVisible"
    :title="title"
    :modal-append-to-body="true"
    :append-to-body="true"
    :close-on-click-modal="false"
    width="${view.editor.size.width}"
    top="${view.editor.size.top}"
    height="${view.editor.size.height}"
  >
    <div class="flex-row h-between v-start">
      <el-form :model="form" :rules="formRules" label-width="${view.editor.labelWidth}">
        <#list view.editor.formItems?keys as key>
          <#list table.fields as field>
            <#if field.propertyName == key>
              <#assign comp = view.editor.formItems[key]>
        <el-form-item label="${field.comment!}">
              <#if comp.component == "el-input">
          <el-input
            v-model="form.${key}"
            :disabled="mode === 2"
            placeholder="请输入${field.comment!}"
            prefix-icon="kt-icon-name-fill"
              <#if comp.type??>
            type="textarea"
              </#if>
          ></el-input>
              <#elseif comp.component == "span">
          <span>{{ form.${field.propertyName} }}</span>
              <#elseif comp.component == "el-checkbox">
          <el-checkbox v-model="form.${field.propertyName}" :disabled="mode === 2">${field.comment!}</el-checkbox>
              <#elseif comp.component == "el-radio-group">
          <el-radio-group v-model="form.${field.propertyName}" :disabled="mode === 2">
                <#list comp.options?keys as op>
            <el-radio-button :label="${comp.options[op]}">${op}</el-radio-button>
                </#list>
          </el-radio-group>
              <#elseif comp.component == "el-time-select">
          <el-time-select v-model="form.${field.propertyName}" :disabled="mode === 2"></el-time-select>
              <#elseif comp.component == "el-date-picker">
          <el-date-picker v-model="form.${field.propertyName}" type="${comp.type}" :disabled="mode === 2"></el-data-picker>
              <#else>
          <${comp.component!}
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
              </#if>
        </el-form-item>
            </#if>
          </#list>
        </#list>
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
  import { save, update, enable, disable } from '@/${apis.path}/${entity?uncap_first}'
  <#else>
  import { save, update } from '@/api/${entity?uncap_first}'
  </#if>

  <#list view.editor.formItems?keys as key>
    <#if !view.editor.formItems[key].component?starts_with("el-") && !view.editor.formItems[key].component?starts_with("kt-") && view.editor.formItems[key].component != "span">
  import ${view.editor.formItems[key].name} from '@/${view.editor.formItems[key].path}'
    </#if>
  </#list>
  const emptyForm = {
<#list table.fields as field>
    ${field.propertyName}: null,
</#list>
  }
  export default {
    name: '${controllerMappingHyphen}-editor',
    components: {},
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
        formRules: {
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
      },
    },
    methods: {
      save() {
        // TODO: add logic code at here
        this.mode === 0 ? this.insert() : this.update()
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
      },
      </#if>
    },
  }
</script>
<style lang="scss" scoped>
</style>
