package com.blinets.sdjpamultidb.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

    @Bean
    @ConfigurationProperties("spring.card.flyway")
    public DataSourceProperties cardFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    public DataSourceProperties cardHolderFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    public DataSourceProperties panFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }
}
