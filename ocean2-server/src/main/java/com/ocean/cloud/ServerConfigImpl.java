package com.ocean.cloud;


import com.ocean.instance.ServerConfig;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ocean Chou
 *
 * server的配置信息
 */
public class ServerConfigImpl implements ServerConfig {

    @Autowired
   ServerConfigProperties registerConfigProperties;

    @Override
    public String getServerId() {
        return registerConfigProperties.getServerId();
    }

    @Override
    public String getInstanceName() {
        return registerConfigProperties.getHostname();
    }

    @Override
    public String getRegisterUrl() {
        return registerConfigProperties.getRegisterUrl();
    }

}
