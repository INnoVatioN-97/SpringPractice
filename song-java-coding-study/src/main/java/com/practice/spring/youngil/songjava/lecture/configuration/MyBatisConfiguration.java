package com.practice.spring.youngil.songjava.lecture.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * MyBatis 설정파일.
 *
 * @author 송자바 님
 */
@Configuration
@MapperScan(basePackages = "com.practice.spring.youngil.songjava.lecture.mvc.repository")
public class MyBatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        /**
         * resources 폴더에 명시한 board.xml 과 같은 myBatis 매핑정보가 담긴 파일
         */
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/sql/*.xml"));
        SqlSessionFactory factory = factoryBean.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
