package com.sf.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.sf"})
@EntityScan("com.sf.bl.entity")
@EnableJpaRepositories(basePackages = "com.sf.bl.jpa")
public class RestConfig {


}
