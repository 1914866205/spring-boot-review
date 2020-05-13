package com.soft1851.springboot.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
 * 1.接口的文档在线自动生成。
 * 2.功能测试。
 * @Author 涛涛
 * @Date 2020/5/13 5:03
 * @Version 1.0
 **/
//凡是，开始某个模块或某个用法@Enable**注解的，放在 整个项目的入口位置，是非常恰当的。
@Configuration
@EnableSwagger2 //对请求类的说明
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.springboot.jpa"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("利用swagger2构建的API文档")
                .description("用RESTful风格写接口")
                .termsOfServiceUrl("http:localhost:8080/")
                .contact(new Contact("1914866205", "https://github.com/1914866205", "1914866205@qq.com"))
                .version("1.0")
                .build();
    }
}
