package com.example.visitlyBackend.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.TimeZone;
import java.util.Collections;
import org.springframework.http.MediaType;

import java.util.Arrays;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.allowed.origin}")
    private String allowedOrigin;

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    // CorsConfiguration configuration = new CorsConfiguration();
    // configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); //
    // Replace with allowed origins
    // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
    // "OPTIONS"));
    // configuration.setAllowedHeaders(Arrays.asList("Authorization",
    // "Content-Type"));
    // configuration.setExposedHeaders(Arrays.asList("Authorization",
    // "Content-Type"));
    // configuration.setAllowCredentials(true);
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", configuration);
    // return source;
    // }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(jsonConverter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin) // Allow your Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true); // If your app uses cookies or authentication headers
    }
}
