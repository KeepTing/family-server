package cn.keepting.family.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family
 * @date:2021/1/7
 **/
@EnableScheduling
@SpringBootApplication
public interface Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
