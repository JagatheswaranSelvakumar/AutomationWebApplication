package com.manulife.webautomation.framework.browserProfile;

import java.util.LinkedHashMap;
import java.util.Map;

public class SafariBrowserProfile extends BrowserProfile
{
    protected Map<String, Boolean> safariCapabilitiesMap;

    public SafariBrowserProfile()
    {
        safariCapabilitiesMap = new LinkedHashMap<String, Boolean>();
    }

    @Override
    public Object createProfile()
    {
        return safariCapabilitiesMap;
    }

}
