package com.yao.project;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口类
 *
 * @author <a href="https://github.com/liyao">程序员鱼皮</a>
 * @from <a href="https://yao.icu">编程导航知识星球</a>
 */
@SpringBootApplication
@MapperScan("com.yao.project.mapper")
@EnableDubbo
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
