package com.blinets.sdjpamultidb.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean(initMethod = "migrate")
    public Flyway flywayCard(@Qualifier("cardFlywayDataSourceProperties")
                                 DataSourceProperties cardFlywayDataSourceProperties) {
        return Flyway.configure()
                .dataSource(cardFlywayDataSourceProperties.getUrl(),
                        cardFlywayDataSourceProperties.getUsername(),
                        cardFlywayDataSourceProperties.getPassword())
                .locations("classpath:/db/migration/card")
                .load();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    public DataSourceProperties cardHolderFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayCardHolder(@Qualifier("cardHolderFlywayDataSourceProperties")
                             DataSourceProperties cardHolderFlywayDataSourceProperties) {
        return Flyway.configure()
                .dataSource(cardHolderFlywayDataSourceProperties.getUrl(),
                        cardHolderFlywayDataSourceProperties.getUsername(),
                        cardHolderFlywayDataSourceProperties.getPassword())
                .locations("classpath:/db/migration/cardholder")
                .load();
    }

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    public DataSourceProperties panFlywayDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayPan(@Qualifier("panFlywayDataSourceProperties")
                                   DataSourceProperties panFlywayDataSourceProperties) {
        return Flyway.configure()
                .dataSource(panFlywayDataSourceProperties.getUrl(),
                        panFlywayDataSourceProperties.getUsername(),
                        panFlywayDataSourceProperties.getPassword())
                .locations("classpath:/db/migration/pan")
                .load();
    }
}
