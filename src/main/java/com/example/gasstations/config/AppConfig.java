package com.example.gasstations.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * Bean definition for creating a ModelMapper instance.
     *
     * @return ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    /**
     * Bean definition for creating a Gson instance.
     *
     * @return Gson instance
     */
    @Bean
    public Gson gson() {
        return new Gson();
    }
}