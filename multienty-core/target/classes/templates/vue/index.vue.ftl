<template>
  <div class="box white fill flex-col scroll-hidden">
    <div class="box gray flex-row h-between">
      <div class="left">
        <#list view.index.searchInputItems as searchItem>
        	<#if searchItem == 'city'>
        <span class="margin-right">城市</span>
        <kt-city-selector
          v-model="searchParams.city"
          class="w150"
        ></kt-city-selector>
            </#if>
            <#if searchItem == "keywords">
        <span class="margin-left margin-right">关键字</span>
        <el-input
          v-model="searchParams.keywords"
          prefix-icon="kt-icon-name-fill"
          placeholder="${view.index.keywordsPlaceHolder}"
          class="w180 margin-right"
        ></el-input>
            </#if>
            <#if searchItem == 'status'>
        <span class="margin-left margin-right">状态</span>
        <kt-status-selector
          v-model="statusObj"
          :option-ids="${view.index.selectableStatus}"
          class="margin-right"
        ></kt-status-selector>
            </#if>
        </#list>
        <el-button type="primary" size="small" @click="getList">搜索</el-button>
        <el-button type="default" size="small" @click="reset">重置</el-button>
      </div>
      <#if view.index.dataCreateEnabled>
      <div class="right">
        <el-button
          v-permission="'add-${controllerMappingHyphen}'"
          type="success"
          icon="kt-icon-add"
          @click="showAdd"
        >
          新增${table.comment!}
        </el-button>
      </div>
      </#if>

    </div>
    <div v-loading="listLoading" class="list-box auto-fill scroll-hidden">
      <el-table
        :data="list"
        header-cell-class-name="u-th-gradient bold"
        :border="true"
        height="100%"
      >
        <#if view.index.table.serialNumberEnabled>
        <el-table-column
          label="序号"
          width="100"
          align="center"
        >
          <template slot-scope="scope">
            {{
              searchParams.pageSize * (searchParams.currentPage - 1) +
              scope.$index +
              1
            }}
          </template>
        </el-table-column>
        </#if>
        <#list view.index.table.tableColumns as column>
          <#list table.fields as f>
            <#if f.propertyName == column>
                <#assign field=f>
            </#if>
          </#list>
          <#if field??>
            <#if (formatter[column])??>
        <el-table-column label="${field.comment!}" prop="${column}" align="center">
          <template slot-scope="scope">
            {{ scope.row.${column} | ${formatter[column]} }}
          </template>
        </el-table-column>
            <#else>
        <el-table-column label="${field.comment!}" prop="${field.propertyName}" align="center" />
            </#if>
          </#if>
        </#list>
        <el-table-column
          label="操作"
          width="180"
          fixed="right"
          header-align="center"
        >
          <template slot-scope="scope">
            <#list view.index.table.tableItemActions as action>
              <#if action == "edit">
            <el-button
              v-permission="'edit-${controllerMappingHyphen}'"
              type="text"
              class="mar-r-10"
              @click="showUpdate(scope.row)"
            >
              编辑
            </el-button>
              <#elseif action == "delete">
            <el-popconfirm
              v-permission="'delete-${controllerMappingHyphen}'"
              :title="`您确定要删除此${table.comment!}吗？`"
              class="mar-r-10"
              @confirm="remove(scope.row)"
            >
              <el-button slot="reference" type="text">删除</el-button>
            </el-popconfirm>
              <#elseif action == "detail">
            <el-button
              v-permission="'view-${controllerMappingHyphen}-detail'"
              type="text"
              class="mar-r-10"
              @click="view(scope.row)"
            >
              查看
            </el-button>
              <#elseif action == "enable">
            <el-popconfirm
              v-if="scope.row.status === 'DISABLED'"
              v-permission="'enable-${controllerMappingHyphen}'"
              :title="`您确定要启用${scope.row.name}吗？`"
              class="mar-r-10"
              @confirm="enable(scope.row)"
            >
              <el-button slot="reference" type="text">启用</el-button>
            </el-popconfirm>
              <#elseif action == "disable">
            <el-popconfirm
              v-if="scope.row.status === 'NORMAL'"
              v-permission="'disable-${controllerMappingHyphen}'"
              :title="`您确定要禁用${scope.row.name}吗？`"
              class="mar-r-10"
              @confirm="disable(scope.row)"
            >
              <el-button slot="reference" type="text">禁用</el-button>
            </el-popconfirm>
              <#elseif action == "web-log">
            <el-button
              v-permission="'view-${controllerMappingHyphen}-log'"
              type="text"
              class="mar-r-10"
              <#list table.fields as fd>
                <#if fd.keyFlag>
              @click="showWebLog(scope.row.${fd.propertyName})"
                </#if>
              </#list>
            >
              日志
            </el-button>
              </#if>
            </#list>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination
      class="margin-top"
      :current-page="searchParams.currentPage"
      :page-size="searchParams.pageSize"
      :pager-count="11"
      layout="prev, pager, next"
      :total="searchParams.total"
      @size-change="onPageSizeChanged"
      @current-change="onPageIndexChanged"
    />
    <${controllerMappingHyphen}-editor
      :visible.sync="editorVisible"
      :mode="editorMode"
      :hospital="current"
      @success="onEditSuccess"
    ></${controllerMappingHyphen}-editor>
    <kt-web-log :visible.sync="webLogVisible" :meta-id="webLogMetaId" />
  </div>
</template>
<script>
  <#list table.fields as field>
      <#if field.propertyName == 'status'>
        <#assign haveStatusColumn = (field.propertyName == 'status')>
      </#if>
  </#list>
  <#if haveStatusColumn??>
  import { getList, remove, enable, disable } from '@/${apis.path}/${entity?uncap_first}'
  <#else>
  import { getList, remove } from '@/${apis.path}/${entity?uncap_first}'
  </#if>
  import ${entity?uncap_first}Editor from './component/${entity?uncap_first}Editor.vue'
  const origSearchParams = {
    currentPage: 1,
    pageSize: 10,
    total: 0,
    keywords: '',
    createTimeDuration: [null, null],
    status: '',
    city: null,
  }
  export default {
    components: {
      ${entity?uncap_first}Editor,
    },
    filters: {
    },
    data() {
      return {
        editorVisible: false,
        editorMode: 0,
        current: null,
        statusObj: {},
        searchParams: JSON.parse(JSON.stringify(origSearchParams)),
        list: [],
        listLoading: false,
      }
    },
    watch: {
      statusObj: {
        handler(newVal) {
          this.searchParams.status = newVal.code
        },
      },
    },
    created() {
      this.getList()
    },
    methods: {
      reset() {
        this.statusObj = {}
        this.searchParams = JSON.parse(JSON.stringify(origSearchParams))
      },
      onEditSuccess(res) {
        this.editorVisible = false
        this.getList()
      },
      view(row) {
        this.current = row
        this.editorMode = 2
        this.editorVisible = true
      },
      showUpdate(row) {
        this.current = row
        this.editorMode = 1
        this.editorVisible = true
      },
      showAdd() {
        this.current = null
        this.editorMode = 0
        this.editorVisible = true
      },
      onPageSizeChanged(pageSize) {
        this.searchParams.pageSize = pageSize
        this.getList()
      },
      onPageIndexChanged(pageIndex) {
        this.searchParams.currentPage = pageIndex
        this.getList()
      },
      <#list table.fields as field>
        <#if field.keyFlag>
      remove(row) {
        remove({ ${field.propertyName}: row.${field.propertyName} }).then((rsp) => {
          this.$notify({
            title: '成功',
            message: `已删除${table.comment!}${'$'}{row.${view.tipsLabelField}}`,
            type: 'success',
          })
          this.getList()
        })
      },
          <#if haveStatusColumn??>
      disable(row) {
        disable({ ${field.propertyName}: row.${field.propertyName} }).then((rsp) => {
          this.$notify({
            title: '成功',
            message: `已禁用${table.comment!}${'$'}{row.${view.tipsLabelField}}`,
            type: 'success',
          })
          this.getList()
        })
      },
      enable(row) {
        enable({ ${field.propertyName}: row.${field.propertyName} }).then((rsp) => {
          this.$notify({
            title: '成功',
            message: `已启用${table.comment!}${'$'}{row.${view.tipsLabelField}}`,
            type: 'success',
          })
          this.getList()
        })
      },
          </#if>
        </#if>
      </#list>
      getList() {
        this.listLoading = true
        getList(this.searchParams)
          .then((rsp) => {
            this.list = rsp.data.records
            this.searchParams.total = rsp.data.total
            this.listLoading = false
          })
          .catch((e) => {
            this.listLoading = false
          })
      },
    },
  }
</script>
<style lang="scss" scoped>
  .container {
    padding: 20px;
    .search-box {
      border-radius: 10px;
      padding: 20px 20px;
      background: #fff;
      width: 100%;
      .ipt {
        width: 150px;
      }
    }
    .list-box {
      margin-top: 30px;
      .label {
        padding: 3px 5px;
        border-radius: 3px;
        background: #e8e8e8;
        margin-right: 5px;
        font-weight: bolder;
      }
    }
  }
</style>