package com.peerstreet.coding.challenge.populationgrowthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PopulationGrowthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopulationGrowthApiApplication.class, args);
	}

}
