package com.desafio.elo7.api.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        //URLs
        String localURL = "http://localhost";


        //Maps Galaxy Controller
        corsRegistry.addMapping("/api/galaxy/get")
                .allowedOrigins(localURL)
                .allowedMethods("GET");

        corsRegistry.addMapping("/api/galaxy/post")
                .allowedOrigins(localURL)
                .allowedMethods("POST");

        //Maps Planet Controller
        corsRegistry.addMapping("/api/planet/{galaxyID}/get")
                .allowedOrigins(localURL)
                .allowedMethods("GET");

        corsRegistry.addMapping("/api/planet/{galaxyID}/post")
                .allowedOrigins(localURL)
                .allowedMethods("POST");

        //Maps Probe Controller
        corsRegistry.addMapping("/api/probe/{planetID}/get")
                .allowedOrigins(localURL)
                .allowedMethods("GET");

        corsRegistry.addMapping("/api/probe/{planetID}/post")
                .allowedOrigins(localURL)
                .allowedMethods("POST");

        corsRegistry.addMapping("/api/probe/{oldPlanetID}/{probeID}/{newPlanetID}/change")
                .allowedOrigins(localURL)
                .allowedMethods("PUT");

        corsRegistry.addMapping("/api/probe/{planetID}/{probeID}/delete")
                .allowedOrigins(localURL)
                .allowedMethods("DELETE");

        //Maps Terminal Controller
        corsRegistry.addMapping("/api/terminal/{planetID}/{probeID}/move")
                .allowedOrigins(localURL)
                .allowedMethods("POST");
    }
}
