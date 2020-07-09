package com.ocean.cloud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.web.context.ServletContextAware;

import java.util.Timer;
import java.util.TimerTask;


import javax.servlet.ServletContext;

import com.ocean.OceanBootStrap;
import com.ocean.instance.ServerConfig;
import com.ocean.registry.Register;

/**
 * @author Ocean Chou
 */
public class ServerContext implements SmartLifecycle{

	private boolean isRunning = false;

	@Autowired
	private OceanBootStrap oceanBootStrap;

	@Autowired
	private ServerConfig serverConfig;

	@Autowired
	private Register register;

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		callback.run();
	}

	@Override
	public void start() {
		new Thread(()->{
			oceanBootStrap.contextInitialized(serverConfig,register);
			ServerContext.this.isRunning = true;
		}).start();
	}

	@Override
	public void stop() {
		this.isRunning = false;
	}

	@Override
	public boolean isRunning() {
		return this.isRunning;
	}

	@Override
	public int getPhase() {
		return 0;
	}



}
