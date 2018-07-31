package com.manulife.webautomation.framework.browserProfile;

import java.util.LinkedHashMap;
import java.util.Map;

public class IEBrowserProfile extends BrowserProfile
{
    protected Map<String, Boolean> ieCapabilitiesMap;

    public IEBrowserProfile()
    {
        ieCapabilitiesMap = new LinkedHashMap<String, Boolean>();
    }

    @Override
    public Object createProfile()
    {
        return ieCapabilitiesMap;
    }

}
