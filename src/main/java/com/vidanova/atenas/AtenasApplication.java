package com.vidanova.atenas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class AtenasApplication{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(AtenasApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AtenasApplication.class, args);

    }



}
