package com.javarush.kotovych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringRunner extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringRunner.class, args);
    }
}
