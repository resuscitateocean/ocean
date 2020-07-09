package com.ocean.cloud;

import javax.servlet.Servlet;

import java.util.Arrays;

import com.ocean.OceanBootStrap;
import com.ocean.controller.ApplicationController;
import com.ocean.controller.InfoCheckController;
import com.ocean.instance.ServerConfig;
import com.ocean.registry.InstanceRegister;
import com.ocean.registry.Register;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ocean Chou
 */
@Configuration
@Import(ServerContext.class) //做容器初始化
@ConditionalOnBean(ServerAutoConfigMarker.class)
@EnableConfigurationProperties(ServerConfigProperties.class)
public class OceanServerAutoConfiguration {

	@Bean
	public ApplicationController applicationController(Register register, ServerConfig serverConfig) {
		ApplicationController applicationController = new ApplicationController(register, serverConfig);
		return applicationController;
	}

	 @Bean
	 public InfoCheckController infoCheckController(){
		return new InfoCheckController();
	 }


	//server的配置信息
	@Bean
	public ServerConfig serverConfig() {
		return new ServerConfigImpl();
	}


	@Bean
	public Register register() {
		InstanceRegister register = new InstanceRegister();
		return register;
	}

	@Bean
	public OceanBootStrap oceanBootStrap(){
		return new OceanBootStrap();
	}


	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
