package com.practice.spring.youngil.songjava.lecture.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Configuration 이 붙었다 = @Bean 과 같이 여러 컴포넌트를 때려박아줄 수 있는 Config 용 클래스임을 스프링에게 알린다.
 */
@Configuration
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") //이 메소드가 실행되며 IoC에 의해 빈이 만들어질 때 spring.datasource에 명시된 DB를 DBSource로 쓰겠다. 라는 것.
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
}
