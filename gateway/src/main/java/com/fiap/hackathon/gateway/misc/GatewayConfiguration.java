package com.fiap.hackathon.gateway.misc;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.fiap.hackathon.gateway")
@EnableJpaAuditing
@EntityScan("com.fiap.hackathon.gateway")
@Configuration
public class GatewayConfiguration {
}
