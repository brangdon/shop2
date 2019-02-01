package com.shopproject.shopproject;

/**
 * Created by admin on 18.01.2019.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
//@PropertySource("classpath:config.properties")
public class CorsClass extends WebMvcConfigurerAdapter {

    //    @Autowired
//    private Environment environment;
//
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/hi")
                .allowedOrigins("http://10.254.5.76:8081", "http://10.254.5.65:8081", "http://10.254.5.64:8081", "http://10.254.5.77:8081");

        registry.addMapping("/auth/signin")
                .allowedOrigins("http://10.254.5.76:3000", "http://10.254.5.65:3000", "http://10.254.5.64:3000", "http://10.254.5.77:3000");

        registry.addMapping("/me")
                .allowedOrigins("http://10.254.5.76:3000", "http://10.254.5.65:3000", "http://10.254.5.64:3000", "http://10.254.5.77:3000");

    }
}