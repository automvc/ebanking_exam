package com.kingstar.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

import com.kingstar.rate.init.TableInit;

@EnableDiscoveryClient
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class RateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateApplication.class, args);
		init();
	}
	
	private static void init() {
		TableInit.createTable();	
	}

}