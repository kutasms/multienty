import request from '@/utils/request'

export function getDetail(data) {
  return request({
    url: '/${entity?uncap_first}/detail',
    method: 'post',
    data,
  })
}

export function getList(data) {
  return request({
    url: '/${entity?uncap_first}/page',
    method: 'post',
    data,
  })
}

export function save(data) {
  return request({
    url: '/${entity?uncap_first}/save',
    method: 'post',
    data,
  })
}

export function update(data) {
  return request({
    url: '/${entity?uncap_first}/update',
    method: 'post',
    data,
  })
}

export function remove(data) {
  return request({
    url: '/${entity?uncap_first}/delete',
    method: 'delete',
    data,
  })
}
<#list table.fields as field>
    <#if field.propertyName == 'status'>
export function disable(data) {
  return request({
    url: '/${entity?uncap_first}/disable',
    method: 'post',
    data,
  })
}
export function enable(data) {
  return request({
    url: '/${entity?uncap_first}/enable',
    method: 'post',
    data,
  })
}
    </#if>
</#list>



