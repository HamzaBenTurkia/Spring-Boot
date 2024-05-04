package com.epi.pfa.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/users/addUser").setViewName("home");
        registry.addViewController("/users/save").setViewName("show-all-users");
        registry.addViewController("/users/showAllUsers").setViewName("show-all-users");
        registry.addViewController("/tags/save").setViewName("show-all-tags");
        registry.addViewController("/tags/showAllTags").setViewName("show-all-tags");
    }

}
