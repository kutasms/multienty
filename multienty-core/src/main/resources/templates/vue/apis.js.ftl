import request from '@/utils/request'
import { microServiceModules } from '@/config'
export function getDetail(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/detail`,
    method: 'post',
    data,
  })
}

export function getList(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/page`,
    method: 'post',
    data,
  })
}

export function save(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/save`,
    method: 'post',
    data,
  })
}

export function update(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/update`,
    method: 'post',
    data,
  })
}

export function remove(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/delete`,
    method: 'delete',
    data,
  })
}
<#list table.fields as field>
    <#if field.propertyName == 'status'>
export function disable(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/disable`,
    method: 'post',
    data,
  })
}
export function enable(data) {
  return request({
    url: `${'$'}{microServiceModules.${serviceModuleName}}/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/enable`,
    method: 'post',
    data,
  })
}
    </#if>
</#list>
