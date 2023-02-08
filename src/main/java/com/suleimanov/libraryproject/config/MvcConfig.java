package com.suleimanov.libraryproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadFile;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // каждое обращение к серверу по пути img/ будет перенапралять по пути file://+"uploadFile"+/
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadFile + "/");
    }
}
