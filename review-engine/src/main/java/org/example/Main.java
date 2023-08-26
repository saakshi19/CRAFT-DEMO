package org.example;

import org.example.review.engine.config.JpaConfig;
import org.example.review.engine.config.ReviewsDaoBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = TaskSchedulingAutoConfiguration.class)
//@SpringBootApplication(exclude = RestTemplateAutoConfiguration.class)
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@Import({ReviewsDaoBean.class, JpaConfig.class})
@SpringBootApplication
@EnableJpaRepositories("org.example.review.engine.dao")
@EntityScan("org.example.review.engine.dao.models")
@Import(ReviewsDaoBean.class)
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }
}
