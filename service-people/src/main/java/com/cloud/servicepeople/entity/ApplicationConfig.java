package com.cloud.servicepeople.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
public class ApplicationConfig {


    @Value("${name}")
    private String name;

    @Value("${age}")
    private String age;

    @Value("${version}")
    private String version;

}
