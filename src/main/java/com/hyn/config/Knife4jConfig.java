package com.hyn.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
// @EnableKnife4j
// @EnableSwagger2
public class Knife4jConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("用户中心接口文档")
                        .description("打造属于自己的用户中心")
                        // .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("knife", "https://knife.blog.csdn.net/", "xx@qq.com"))
                        .version("1.0")
                        .build())
                // 分组名称
                .groupName("all")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hyn.controller"))
                .paths(PathSelectors.any())
                .build();
 
        return docket;
    }
}