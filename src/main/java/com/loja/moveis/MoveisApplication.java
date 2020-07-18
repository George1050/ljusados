package com.loja.moveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class MoveisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoveisApplication.class, args);
    }

    @Bean
    WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry){
                registry.addResourceHandler("static/css/**")
                        .addResourceLocations("classpath:/static/");
            }
        };
    }

}
