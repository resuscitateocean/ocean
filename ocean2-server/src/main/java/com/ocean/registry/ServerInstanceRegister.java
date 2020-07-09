package com.ocean.registry;

import java.util.List;
import java.util.Map;

import com.ocean.Application;
import com.ocean.instance.ServerInstanceInfo;

/**
 * @author Ocean Chou
 */
public class ServerInstanceRegister {

	public void register(String callerUrl, ServerInstanceInfo serverInstanceInfo){
		String otherServerUrl = serverInstanceInfo.getRegisterUrl();
		Map<String, List<String>> serverWithServers = Application.getServerWithServers();
		List<String> otherServersUrl = serverWithServers.get(callerUrl);
		otherServersUrl.add(serverInstanceInfo.getRegisterUrl());
	}

}
