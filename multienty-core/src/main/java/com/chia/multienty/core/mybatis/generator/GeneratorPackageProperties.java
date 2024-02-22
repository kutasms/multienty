package com.chia.multienty.core.mybatis.generator;

import lombok.Data;

@Data
public class GeneratorPackageProperties {
    private String controller;
    private String entity;
    private String service;
    private String mapper;
    private String serviceImpl;
    private String dto;

    private String parameter;

}
