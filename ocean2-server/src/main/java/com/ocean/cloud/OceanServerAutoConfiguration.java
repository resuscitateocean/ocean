package com.ocean.cloud;

import javax.servlet.Servlet;

import java.util.Arrays;

import com.ocean.controller.ApplicationController;
import com.ocean.instance.InstanceConfig;
import com.ocean.registry.Register;
import com.ocean.servlet.ResourceInstance;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Ocean Chou
 */
@Configuration
@Import(ServerContext.class) //做容器初始化 以及服务剔除功能实现(定时器)
@ConditionalOnBean(ServerAutoConfigMarker.class)
@EnableConfigurationProperties(RegisterConfigProperties.class)
public class OceanServerAutoConfiguration {

	@Bean
	public ApplicationController applicationController(Register register, InstanceConfig instanceConfig) {
		ApplicationController applicationController = new ApplicationController(register, instanceConfig);
		return applicationController;
	}

	@Bean
	public InstanceConfig instanceConfig() {
		return new InstanceConfigImpl();
	}


	@Bean
	public ResourceInstance resourceInstance(ApplicationController applicationController) {
		ResourceInstance resourceInstance = new ResourceInstanceImpl();
		resourceInstance.getResourceInstances().put(ApplicationController.class, applicationController);
		return resourceInstance;

	}


	@Bean
	public Register register() {
		EventInstanceRegister register = new EventInstanceRegister();
		return register;
	}

}
