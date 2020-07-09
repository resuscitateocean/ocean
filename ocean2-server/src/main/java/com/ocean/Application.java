package com.ocean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.ocean.instance.InstanceInfo;
import com.ocean.instance.Lease;
import com.ocean.instance.ServerInstanceInfo;


/**
 * @author Ocean Chou
 */

/**
 * 注册中心，存储所有注册表
 */
public class Application {

	//每个客户端实例的注册表
	private static ConcurrentHashMap<String, Map<String, Lease<InstanceInfo>>>
			registerInfo = new ConcurrentHashMap();

	//所有的服务端注册表
	private static List<Map<String, ServerInstanceInfo>> servers = new CopyOnWriteArrayList();

	//服务端互相注册的注册表
	private static Map<String,List<String>> serverWithServers = new ConcurrentHashMap<>();

	//服务端存储客户端注册信息的注册表
	private static Map<String, ConcurrentHashMap> serverWithClients = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, Map<String, Lease<InstanceInfo>>> getRegisterInfo() {
		return registerInfo;
	}

	public static List getServers() {
		return servers;
	}

	public static Map<String, ConcurrentHashMap> getServerWithClients() {
		return serverWithClients;
	}

	public static Map<String, List<String>> getServerWithServers() {
		return serverWithServers;
	}
}
