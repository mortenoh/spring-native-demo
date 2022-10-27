package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplateBuilder().defaultMessageConverters()
            .defaultHeader( "Content-Type", MediaType.APPLICATION_JSON_VALUE )
            .defaultHeader( "Accept", MediaType.APPLICATION_JSON_VALUE )
            .basicAuthentication( "admin", "district", StandardCharsets.UTF_8 )
            .build();
    }
}
