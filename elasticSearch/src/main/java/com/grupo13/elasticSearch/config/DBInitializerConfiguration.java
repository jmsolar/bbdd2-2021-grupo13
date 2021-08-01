package com.grupo13.elasticSearch.config;

import com.grupo13.elasticSearch.utils.DBInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializerConfiguration {
    @Bean
    public DBInitializer createDBInitializer() {
        return new DBInitializer();
    }
}
