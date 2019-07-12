package com.zile.beetlsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BeetlSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeetlSqlApplication.class, args);
    }

}
