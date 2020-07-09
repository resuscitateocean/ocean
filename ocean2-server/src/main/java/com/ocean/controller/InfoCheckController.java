package com.ocean.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ocean.Application;
import com.ocean.instance.InstanceInfo;
import com.ocean.instance.Lease;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ocean Chou
 */
@RestController
public class InfoCheckController {


	@GetMapping("/client-register.do")
	public ConcurrentHashMap<String, Map<String, Lease<InstanceInfo>>> getClientRegisteredTable(){
		return Application.getRegisterInfo();
	}


}
