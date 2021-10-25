package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Component
@PropertySource(value= {"application.properties"})
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig extends HikariConfig{ 
	
	@Autowired
	private Environment environment;
	
	@Bean
	public HikariDataSource primaryDataSource() {
		
		String username = DES.decrypt(environment.getProperty("spring.datasource.username"));
		String password = DES.decrypt(environment.getProperty("spring.datasource.password"));
		
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
	    config.setJdbcUrl(environment.getProperty("spring.datasource.jdbcUrl"));
	    config.setUsername(username);
	    config.setPassword(password);
        final HikariDataSource ds = new HikariDataSource(config); 
	    return  ds;
	}
	
}
