package com.ocean.cloud;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author Ocean Chou
 */
@ConfigurationProperties("ocean.server")
@Data
public class ServerConfigProperties {

    private String serverId;
    private String hostname;
    private String registerUrl;


}
