package com.practice.spring.youngil.songjava.lecture.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 설정 클래스.
 * Swagger 는 API 사용법에 관한 문서를 만들어 준다.
 * 사용자 요구에 따라 API 의 구조가 변경될 때마다 문서를 수정하는 것은
 * 엄청나게 피곤한 일이다.
 * Swagger 는 소스를 기반으로 API 문서를 만들어주고
 * 테스트까지 할 수 있는 라이브러리이다.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket(){
        ApiInfoBuilder apiInfo = new ApiInfoBuilder();
        apiInfo.title("API 서버 문서");
        apiInfo.description("API 서버 문서입니다.");

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo.build());

        ApiSelectorBuilder apis = docket.select().apis(RequestHandlerSelectors.basePackage("com.practice.spring.youngil.songjava.lecture.mvc.controller"));
        apis.paths(PathSelectors.ant("/**"));
        System.out.println("Swagger 동작");
        return apis.build();
    }
}
