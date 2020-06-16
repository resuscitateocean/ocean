package com.ocean.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ocean.instance.InstanceInfo;
import com.ocean.instance.Lease;

/**
 * @author Ocean Chou
 */
public class InstanceRegister implements Register {

	private ConcurrentHashMap<String, Map<String, Lease<InstanceInfo>>>
			register = new ConcurrentHashMap();


	@Override
	public void register(InstanceInfo info, int timeLimit, boolean isSync) {
		System.out.println("调用服务注册 info:"+info);

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
