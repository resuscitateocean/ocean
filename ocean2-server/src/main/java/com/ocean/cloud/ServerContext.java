package com.ocean.cloud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;

import java.util.Timer;
import java.util.TimerTask;

import com.ocean.instance.InstanceConfig;
import com.ocean.registry.Register;

/**
 * @author Ocean Chou
 */
public class ServerContext implements SmartLifecycle {

    private boolean isRunning = false;

    @Autowired
    private InstanceConfig instanceConfig;

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
        new  Thread(){
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        register.eviction();
                    }
                },instanceConfig.getExpelTimerMs(),instanceConfig.getExpelTimerMs());
            }
        }.start();
    }



    @Override
    public void stop() {
        this.isRunning =false;
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
