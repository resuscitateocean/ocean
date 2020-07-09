package com.ocean.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ocean.Application;
import com.ocean.instance.InstanceInfo;
import com.ocean.instance.Lease;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ocean Chou
 */
@Slf4j
public class InstanceRegister implements Register {


	@Override
	public void register(InstanceInfo info, int timeLimit, boolean isSync) {

		ConcurrentHashMap<String, Map<String, Lease<InstanceInfo>>> registerInfo = Application.getRegisterInfo();

		log.info("开始服务注册，实例信息："+info);
		String instanceName = info.getInstanceName();
		String instanceId = info.getInstanceId();
		Map<String, Lease<InstanceInfo>> selfInfo = new ConcurrentHashMap<>();
		selfInfo.put(instanceId,new Lease<>(info,System.currentTimeMillis(),timeLimit));
		registerInfo.put(instanceName,selfInfo);
	}

	@Override
	public void renew(String appName, String instanceId, boolean isSync) {
		System.out.println("调用心跳续约");
	}

	@Override
	public void cancel(String appName, String instanceId, boolean isSync) {
		System.out.println("调用服务下架");
	}

	@Override
	public void eviction() {
		System.out.println("调用服务剔除");
	}
}
