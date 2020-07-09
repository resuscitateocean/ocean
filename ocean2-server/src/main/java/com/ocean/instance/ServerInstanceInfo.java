package com.ocean.instance;

import java.util.Map;

import com.ocean.Application;
import com.ocean.constant.InstanceStatus;
import lombok.Data;

/**
 * @author Ocean Chou
 */
@Data
public class ServerInstanceInfo {
	private String serverId;
	private String hostName;
	private String registerUrl;
	private InstanceStatus instanceStatus;


}
