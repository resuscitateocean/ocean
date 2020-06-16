package com.ocean.test;

import com.ocean.cloud.EnableOceanServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOceanServer
public class OceanServerMain {
	public static void main(String[] args) {
		SpringApplication.run(OceanServerMain.class,args);
	}
}
