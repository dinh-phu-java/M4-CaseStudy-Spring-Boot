package com.dinhpu.m4casestudy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String evnUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir= Paths.get(evnUploadPath);
        String uploadPath=uploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/upload_file/**").addResourceLocations("file:/"+uploadPath+"/");

    }
}
