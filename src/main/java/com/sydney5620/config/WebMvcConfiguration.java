package com.sydney5620.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // allow all path
                .allowedOrigins("http://localhost:3000") // all request from localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")// Allowed HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }


    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("AI Assistant API")
                .version("3.0")
                .description("an AI Assistant")
                .contact(new Contact("Keliang Liu", "unknown","keliang123123@gmail.com"))
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                //Specify the package to scan for generating interfaces
                .apis(RequestHandlerSelectors.basePackage("com.sydney5620.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("start configuring static resource mapping...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}




