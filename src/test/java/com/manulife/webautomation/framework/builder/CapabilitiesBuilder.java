package com.manulife.webautomation.framework.builder;

import org.openqa.selenium.remote.DesiredCapabilities;
import com.manulife.webautomation.framework.browserProfile.BrowserProfile;

public abstract class CapabilitiesBuilder {
    
    public abstract CapabilitiesBuilder addBrowser(String browser);

    public abstract CapabilitiesBuilder addVersion(String version);

    public abstract CapabilitiesBuilder addPlatform(String platform);

    public abstract CapabilitiesBuilder addBrowserProfile(BrowserProfile browserProfile);

    public abstract DesiredCapabilities build();

    public abstract CapabilitiesBuilder addBrowserDriverExecutablePath(String path);
    
    
}
