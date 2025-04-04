package com.neu.csye6220.libseatmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LibrarySeatManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySeatManagementSystem.class, args);
    }

}
