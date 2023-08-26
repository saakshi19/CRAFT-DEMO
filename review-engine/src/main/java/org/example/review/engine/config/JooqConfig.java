package org.example.review.engine.config;

//import org.jooq.DSLContext;
//import org.jooq.SQLDialect;
//import org.jooq.impl.DSL;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url("jdbc:postgresql://localhost:5432/postgres")
//                .username("postgres")
//                .password("postgres")
//                .build();
//    }

//    @Bean
//    public DSLContext dslContext(DataSource dataSource) {
//        return DSL.using(dataSource, SQLDialect.POSTGRES);
//    }
}