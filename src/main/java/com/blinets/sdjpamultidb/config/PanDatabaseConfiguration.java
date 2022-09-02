package com.blinets.sdjpamultidb.config;

import com.blinets.sdjpamultidb.domain.pan.CreditCardPan;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.blinets.sdjpamultidb.repositories.pan",
        entityManagerFactoryRef = "panEntityManagerFactoryBean",
        transactionManagerRef = "panPlatformTransactionManager")
public class PanDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.pan.datasource.hikari")
    public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties) {
        return panDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactoryBean(
            @Qualifier("panDataSource") DataSource panDataSource,
            EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean pan = builder.dataSource(panDataSource)
                .packages(CreditCardPan.class)
                .persistenceUnit("pan")
                .build();
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        pan.setJpaProperties(properties);
        return pan;
    }

    @Primary
    @Bean
    public PlatformTransactionManager panPlatformTransactionManager(
            @Qualifier("panEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean panEntityManagerFactoryBean) {
        return new JpaTransactionManager(panEntityManagerFactoryBean.getObject());
    }

}
