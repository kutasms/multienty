package com.chia.multienty.core.mybatis.generator;

import lombok.Data;

@Data
public class GeneratorFormatterProperties {
    private String controller;
    private String entity;
    private String service;
    private String mapper;
    private String serviceImpl;
    private String xml;
}
