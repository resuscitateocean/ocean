package com.ocean.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author Ocean Chou
 */
@ConfigurationProperties("ocean.register")
public class RegisterConfigProperties {

    private String hostname;

    @Value("${ocean.register.registerUrl}")
    private String registerUrl;

    private int expelTimerMs;

    public int getExpelTimerMs() {
        return expelTimerMs;
    }

    public void setExpelTimerMs(int expelTimerMs) {
        this.expelTimerMs = expelTimerMs;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }
}
