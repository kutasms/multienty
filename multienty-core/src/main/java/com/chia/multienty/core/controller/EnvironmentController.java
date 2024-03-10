package com.chia.multienty.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/env")
public class EnvironmentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/port")
    public String getServerPort() {
        return "Gateway port:" + serverPort;
    }
}

