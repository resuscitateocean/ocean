package com.ocean.registry;

import com.ocean.instance.InstanceInfo;

/**
 * @author Ocean Chou
 */
public interface Register {

	void register(InstanceInfo info, int timeLimit, boolean isSync);

	void  renew(String appName, String instanceId, boolean isSync);

	void cancel(String appName, String instanceId, boolean isSync);

	void eviction();
}
