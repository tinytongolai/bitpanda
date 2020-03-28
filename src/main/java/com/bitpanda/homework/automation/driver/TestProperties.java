package com.bitpanda.homework.automation.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestProperties {
    private DesiredCapabilities capabilities;
    private String webUrl;
    private String remoteUrl;
    private String run = "LOCAL";

    public void setCapabilities(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public Platform getPlatform() {
        if (capabilities == null) {
            throw new RuntimeException("You did not set capabilities.");
        }
        return capabilities.getPlatform();
    }

    public String getBrowserName() {
        if (capabilities == null) {
            throw new RuntimeException("You did not set capabilities.");
        }
        return capabilities.getBrowserName();
    }

    public boolean isRemote() {
        return run.equals("REMOTE");
    }

    public void setRun(String run) {
        this.run = run;
    }

    public DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }
}
