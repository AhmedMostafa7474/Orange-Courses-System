package com.example.Spring1;

import com.example.Spring1.Repo.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationMain.class,args);
    }
}
