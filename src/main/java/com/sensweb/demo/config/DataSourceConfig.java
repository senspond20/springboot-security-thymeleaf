package com.sensweb.demo.config;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
    
    @Bean(name="dataSource")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/springweb?useSsl=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("1234");

        config.setAutoCommit(false);
        config.setConnectionInitSql("SELECT 1");
        config.setPoolName("springHikariCP");
        config.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        config.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        config.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        // DataSourceBuilder<HikariDataSource> ds =  DataSourceBuilder.create().type(HikariDataSource.class);
        // ds.driverClassName("com.mysql.cj.jdbc.Driver");
        // ds.username("root");
        // ds.password("1234");
        // ds.url("jdbc:mysql://localhost:3306/springweb?useSsl=false&serverTimezone=UTC");
        // return ds.build();

        return new HikariDataSource(config);
    }
}
