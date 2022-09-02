package com.blinets.sdjpamultidb.config;

import com.blinets.sdjpamultidb.domain.cardholder.CreditCardHolder;
import com.blinets.sdjpamultidb.domain.creditcard.CreditCard;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CardHolderDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.cardholder.datasource")
    public DataSourceProperties cardHolderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cardHolderDataSource(@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties){
        return cardHolderDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactoryBean(
            @Qualifier("cardHolderDataSource") DataSource cardHolderDataSource,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(cardHolderDataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardholder")
                .build();
    }
    @Bean
    public PlatformTransactionManager cardHolderPlatformTransactionManager(
            @Qualifier("cardHolderEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactoryBean) {
        return new JpaTransactionManager(cardHolderEntityManagerFactoryBean.getObject());
    }
}
