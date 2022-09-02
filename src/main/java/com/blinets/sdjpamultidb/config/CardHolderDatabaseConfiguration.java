package com.blinets.sdjpamultidb.config;

import com.blinets.sdjpamultidb.domain.cardholder.CreditCardHolder;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.blinets.sdjpamultidb.repositories.cardholder",
        entityManagerFactoryRef = "cardHolderEntityManagerFactoryBean",
        transactionManagerRef = "cardHolderPlatformTransactionManager")
public class CardHolderDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.cardholder.datasource.hikari")
    public DataSource cardHolderDataSource(@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties) {
        return cardHolderDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactoryBean(
            @Qualifier("cardHolderDataSource") DataSource cardHolderDataSource,
            EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean cardholder = builder.dataSource(cardHolderDataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardholder")
                .build();
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");

        cardholder.setJpaProperties(properties);
        return cardholder;
    }

    @Bean
    public PlatformTransactionManager cardHolderPlatformTransactionManager(
            @Qualifier("cardHolderEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactoryBean) {
        return new JpaTransactionManager(cardHolderEntityManagerFactoryBean.getObject());
    }
}
