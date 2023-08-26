package org.example.review.engine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.review.engine.dao") // Specify the package containing ReviewsDao
public class ReviewsDaoBean {
}
