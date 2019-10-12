package com.bta.axondemo.infra.sql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.bta.axondemo.infra.sql")
public class JpaConfig {

}
