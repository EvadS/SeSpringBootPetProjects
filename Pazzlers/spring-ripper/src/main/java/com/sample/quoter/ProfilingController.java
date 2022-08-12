package com.sample.quoter;

/**
 * Created by Evgeniy Skiba on 12.03.21
 */
public class ProfilingController  implements  ProfilingControllerMBean{
    private  boolean enabled;

    public ProfilingController() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
