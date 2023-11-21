package com.jef.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Jef
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9001.class, args);
    }
}