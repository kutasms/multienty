<template>
  <div class="box white fill flex-col scroll-hidden">
    <div class="box pad white flex-row h-between bottom-border mar-b-10">
      <div class="search-left-box flex-row h-start">
        <el-form label-width="55px">
          <div class="flex-col h-start">
            <div class="flex-row h-start">
        <#list view.index.searchInputItems as searchItem>
            <#if searchItem == "keywords">
              <el-form-item label="关键字">
                <el-input
                  v-model="searchParams.keywords"
                  prefix-icon="kt-icon-name-fill"
                  placeholder="请输入搜索关键字"
                ></el-input>
              </el-form-item>
            </#if>
            <#if searchItem == 'status'>
              <el-form-item label="状态">
                <kt-status-selector
                  v-model="statusObj"
                  :option-ids="${view.index.selectableStatus}"
                  class="margin-right"
                ></kt-status-selector>
              </el-form-item>
            </#if>
            <#if searchItem == 'city'>
              <el-form-item label="城市">
                <kt-city-selector
                  v-model="searchParams.city"
                  class="w150"
                ></kt-city-selector>
              </el-form-item>
            </#if>
        </#list>
            </div>
          </div>
        </el-form>
        <div class="flex-row h-start v-end mar-l-10">
          <el-button
            icon="el-icon-s-operation"
            type="default"
            @click="toggleMoreSearchOption"
          >
            {{ moreSearchOptionVisible ? '折叠' : '展开' }}
          </el-button>
        </div>
      </div>
      <div class="search-right-box flex-row h-end">
        <div>
          <el-button
            type="primary"
            size="small"
            icon="kt-icon-search"
            @click="getList"
          >
            搜索
          </el-button>
          <el-button
            type="default"
            size="small"
            icon="kt-icon-reset"
            @click="reset"
          >
            重置
          </el-button>
        </div>
      </div>
    </div>
    <div class="action-box box rad-none flex-row h-between v-center">
      <div class="left pad-l-10">
        <el-button
          icon="kt-icon-batch-select"
          size="mini"
          @click="selectAll($refs.table, list)"
        >
          全选
        </el-button>
        <el-button
          icon="kt-icon-batch-cancel-select"
          size="mini"
          @click="unselectAll($refs.table, list)"
        >
          取消全选
        </el-button>
        <el-button
          icon="kt-icon-batch-del"
          size="mini"
          :loading="executing"
          @click="removeSelected"
        >
          批量删除
        </el-button>
      </div>
      <div class="right pad-r-10">
        <el-button
          v-permission="'add-${controllerMappingHyphen}'"
          type="success"
          icon="kt-icon-add"
          size="mini"
          @click="showAdd"
        >
          新增${table.comment!}
        </el-button>
        <el-button
          type="default"
          icon="kt-icon-download"
          size="mini"
          @click="export2Excel($refs.table)"
        >
          导出
        </el-button>
        <el-button
          type="default"
          icon="el-icon-printer"
          size="mini"
          @click="printTable('table')"
        >
          打印
        </el-button>
        <el-popover
          placement="bottom-start"
          title="请选择隐藏列"
          width="200"
          trigger="click"
          class="mar-l-10"
        >
          <el-table
            v-if="$refs.table"
            ref="columnFilterTable"
            :data="columnData"
            @selection-change="onColumnFilterChange"
          >
            <el-table-column type="selection"></el-table-column>
            <el-table-column label="数据列" prop="label"></el-table-column>
          </el-table>
          <el-button
            slot="reference"
            icon="kt-icon-filter"
            type="default"
            size="mini"
          >
            筛选项
          </el-button>
        </el-popover>
      </div>
    </div>
    <div v-loading="listLoading" class="list-box auto-fill scroll-hidden">
      <el-table
        id="table"
        ref="table"
        :data="list"
        header-cell-class-name="u-th-gradient bold"
        :border="true"
        fit
        stripe
        height="100%"
        @sort-change="onTableSortChange"
        @selection-change="onSelectionChange"
        @row-dblclick="(row) => toggleRow($refs.table, row)"
      >
        <el-table-column type="selection"></el-table-column>
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
        <el-table-column
          v-if="isColumnShown('${column}')"
          label="${field.comment!}"
          prop="${column}"
          align="center"
        >
          <template slot-scope="scope">
            {{ scope.row.${column} | ${formatter[column]} }}
          </template>
        </el-table-column>
            <#else>
        <el-table-column
           v-if="isColumnShown('${field.propertyName}')"
           label="${field.comment!}"
           prop="${field.propertyName}"
           align="center" />
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
              @click="showWebLog(scope.row.${fd.propertyName}, '${entity}')"
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
      v-if="editorVisible"
      :visible.sync="editorVisible"
      :mode="editorMode"
      :value="current"
      @success="onEditSuccess"
    ></${controllerMappingHyphen}-editor>
    <kt-web-log
      :visible.sync="webLogVisible"
      :meta-id="webLogMetaId"
      :target="webLogTarget"
      :type="webLogType"
    />
  </div>
</template>
<script>
  <#list table.fields as field>
      <#if field.propertyName == 'status'>
        <#assign haveStatusColumn = (field.propertyName == 'status')>
      </#if>
  </#list>
  <#if haveStatusColumn??>
  import { getList, remove, enable, disable } from '@/${apis.path}/${pkg}/${entity?uncap_first}'
  <#else>
  import { getList, remove } from '@/${apis.path}/${pkg}/${entity?uncap_first}'
  </#if>
  import ${entity?uncap_first}Editor from './component/${entity?uncap_first}Editor.vue'
  import { tableMixin } from '@/mixins/table'
  const origSearchParams = {
    currentPage: 1,
    pageSize: 10,
    total: 0,
    keywords: '',
    createTimeDuration: [null, null],
    status: null,
    city: null,
    orderBy: null
  }
  export default {
    components: {
      ${entity?uncap_first}Editor,
    },
    filters: {
    },
    mixins: [tableMixin],
    data() {
      return {
        editorVisible: false,
        editorMode: 0,
        current: null,
        statusObj: {},
        searchParams: JSON.parse(JSON.stringify(origSearchParams)),
        list: [],
        listLoading: false,
        moreSearchOptionVisible: false
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
      onTableSortChange({ column, prop, order }) {
        this.searchParams.orderBy = { prop, order }
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
        .catch((e) => {
          console.log(e)
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
        .catch((e) => {
          console.log(e)
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
        .catch((e) => {
          console.log(e)
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