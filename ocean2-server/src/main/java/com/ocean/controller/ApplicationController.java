package com.ocean.controller;


import com.ocean.instance.InstanceConfig;
import com.ocean.instance.InstanceInfo;
import com.ocean.instance.Lease;
import com.ocean.registry.Register;
import com.ocean.util.R;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ocean Chou
 */
@RestController
public class ApplicationController {

	private Register register;

	private InstanceConfig instanceConfig;

	public ApplicationController (Register register,InstanceConfig instanceConfig){
		this.register = register;
		this.instanceConfig = instanceConfig;
	}


	//测试链接http://localhost/register.do?ipAddr=127.0.0.1&hostName=xxx&instanceId=clinet-001&instanceName=client&timeLimit=4000&port=8080
	@RequestMapping(value = "/register.do")
	public R register(InstanceInfo instanceInfo, String isSync) {
		System.out.println("registttter!!!!");
		if (isNull(instanceInfo.getHostName())) {
			return R.error("HostName 不能为空").set("code",400);
		} else if (isNull(instanceInfo.getInstanceId())) {
			return R.error("InstanceId 不能为空").set("code",400);
		} else if (isNull(instanceInfo.getInstanceName())) {
			return R.error("InstanceName 不能为空").set("code",400);
		} else if (isNull(instanceInfo.getPort())) {
			return R.error("Port 不能为空").set("code",400);
		} else if (isNull(instanceInfo.getIpAddr())) {
			return R.error("IpAddr 不能为空").set("code",400);
		}

		int timeLimit = Lease.DEFULT_TIME_LIMIT;
		if (instanceInfo.getTimeLimit()>0) {
			timeLimit = instanceInfo.getTimeLimit();
		}
		register.register(instanceInfo,timeLimit,isSync==null?false:isSync.equals("true"));
		return R.success();
	}


	@RequestMapping("/renew.do")
	public R renew(String instanceName,String instanceId,String isSync){
		if (isNull(instanceName)){
			return R.error("instanceName 不能为空").set("code",400);
		}else if(isNull(instanceId)){
			return R.error("InstanceId 不能为空").set("code",400);
		}
		register.renew(instanceName,instanceId,isSync==null?false:isSync.equals("true"));
		return R.success();
	}

	@RequestMapping("/cancel.do")
	public R cancel(String instanceName,String instanceId,String isSync){
		if (isNull(instanceName)){
			return R.error("instanceName 不能为空").set("code",400);
		}else if(isNull(instanceId)){
			return R.error("instanceId 不能为空").set("code",400);
		}
		register.cancel(instanceName,instanceId,isSync==null?false:isSync.equals("true"));
		return  R.success();
	}

	public boolean isNull(String str) {
		return str == null || str.isEmpty();
	}


}
