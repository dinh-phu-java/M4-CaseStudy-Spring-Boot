package com.dinhpu.m4casestudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir= Paths.get("./upload_file");
        String uploadPath=uploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/upload_file/**").addResourceLocations("file:/"+uploadPath+"/");
    }
}
