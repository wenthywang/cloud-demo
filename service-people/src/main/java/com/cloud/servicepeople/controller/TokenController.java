package com.cloud.servicepeople.controller;

import cn.hutool.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/token")
public class TokenController {


    protected static Logger logger = LoggerFactory.getLogger(TokenController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Mono<String> getToken() {

        return Mono.create(resp -> {
            JSONObject obj = new JSONObject();
            obj.put("status", true);
            obj.put("token", GetGUID());
            resp.success(obj.toString());
        });
    }


    public String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

