package com.ocean.instance;


import com.ocean.constant.InstanceStatus;
import lombok.Data;

/**
 * 实例信息
 * @author Ocean Chou
 */
@Data
public class InstanceInfo {

	//ip地址
	private String ipAddr;
	//主机名
	private String hostName;
	//实例id
	private String instanceId;
	//实例名
	private String instanceName;
	//过期时间
	private int timeLimit;
	//端口号
	private String port;
	//实例状态
	private InstanceStatus instanceStatus;

	@Override
	public String toString() {
		return "InstanceInfo{" +
				"ipAddr='" + ipAddr + '\'' +
				", hostName='" + hostName + '\'' +
				", instanceId='" + instanceId + '\'' +
				", instanceName='" + instanceName + '\'' +
				", timeLimit=" + timeLimit +
				", port='" + port + '\'' +
				", instanceStatus=" + instanceStatus +
				'}';
	}

}