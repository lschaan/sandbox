package com.lschaan.poc.springbatch.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "dbEntityManagerFactory", basePackages = "com.lschaan.poc.springbatch.repository")
public class DBDataSourceConfig {

    @Bean(name = "dbDataSource")
    public DataSource dataSource(@Qualifier("dbDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("dbDataSourceProperties")
    @ConfigurationProperties("app.datasource.db")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(@Qualifier("dbDataSource") DataSource dataSource) {
        return new LocalContainerEntityManagerFactoryBean() {
            {
                setDataSource(dataSource);
                setPackagesToScan("com.lschaan.poc.springbatch.dto");
                setPersistenceProvider(new HibernatePersistenceProvider());
            }
        };
    }
}
