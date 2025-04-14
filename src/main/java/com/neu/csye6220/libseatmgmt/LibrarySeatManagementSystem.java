package com.neu.csye6220.libseatmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EntityScan(basePackages = "com.neu.csye6220.libseatmgmt.model")
public class LibrarySeatManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySeatManagementSystem.class, args);
    }

}
