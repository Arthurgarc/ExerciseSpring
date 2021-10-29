package com.example.springclienteprojeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringClienteProjetoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringClienteProjetoApplication.class, args);
    }
}
