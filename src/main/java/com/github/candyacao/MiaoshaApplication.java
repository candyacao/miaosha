package com.github.candyacao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.candyacao.mapper")
public class MiaoshaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoshaApplication.class, args);
    }
}
