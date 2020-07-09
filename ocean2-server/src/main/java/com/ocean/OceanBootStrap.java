package com.ocean;

import java.lang.reflect.AccessibleObject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import com.ocean.constant.InstanceStatus;
import com.ocean.instance.ServerConfig;
import com.ocean.instance.ServerInstanceInfo;
import com.ocean.registry.Register;
import com.ocean.util.R;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * @author Ocean Chou
 */
@Slf4j
public class OceanBootStrap {

	@Autowired
	private RestTemplate restTemplate;

	public void contextInitialized(ServerConfig serverConfig, Register register) {
		String registerUrl = serverConfig.getRegisterUrl();
		String instanceName = serverConfig.getInstanceName();

		ServerInstanceInfo serverInstanceInfo = new ServerInstanceInfo();
		serverInstanceInfo.setHostName(instanceName);
		serverInstanceInfo.setRegisterUrl(registerUrl);
		serverInstanceInfo.setInstanceStatus(InstanceStatus.UP);

		Map<String, ServerInstanceInfo> serverInstanceInfoMap = Collections
				.singletonMap(registerUrl, serverInstanceInfo);

		Application.getServers().add(serverInstanceInfoMap);

		//做server的同步
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.schedule(()->{
			registerWithOtherServers(serverInstanceInfo,register);
		},10,TimeUnit.SECONDS);

		registerWithOtherServers(serverInstanceInfo,register);

	}

	private void registerWithOtherServers(ServerInstanceInfo serverInstanceInfoSelf, Register register) {
		List<Map<String,ServerInstanceInfo>> servers = Application.getServers();

		for (Map<String, ServerInstanceInfo> server : servers) {
			if(!server.equals(serverInstanceInfoSelf)){
				server.forEach((k,v)->{
					if(v.getRegisterUrl() != null) {
						ResponseEntity<R> entity = restTemplate.
								getForEntity(v.getRegisterUrl(), R.class, serverInstanceInfoSelf
										.getRegisterUrl(), serverInstanceInfoSelf);

						if (entity.getBody().get(R.CODE_KEY).equals(R.SUCCESS_CODE)) {
							log.info(serverInstanceInfoSelf.getRegisterUrl() + " 注册到 " + v.getRegisterUrl() + " 成功！");
						}
					}
				});

			}
		}
	}

}