package fr.lavachequicode.lib.upnp.services;

import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;

@UpnpService(
        serviceId = @UpnpServiceId(namespace = "denon-com", value = "ACT"),
        serviceType = @UpnpServiceType(namespace = "schemas-denon-com", value = "ACT", version = 1)
)
public interface ACT {

    ServiceId serviceId = new ServiceId("denon-com", "ACT");

    @UpnpAction(name = "GetHEOSNetID", out = @UpnpOutputArgument(name = "HEOSNetID", stateVariable = "ARG_HeosNetId"))
    String getHEOSNetID();

    @UpnpAction(name = "GetLEDConfig", out = @UpnpOutputArgument(name = "LEDConfig", stateVariable = "LEDConfig"))
    String getLEDConfig();

    @UpnpAction(name = "CancelFirmwareUpgrade")
    void cancelFirmwareUpgrade();

    @XML
    @UpnpAction(name = "GetCurrentState", out = @UpnpOutputArgument(name = "CurrentState"))
    String getCurrentState();

    @UpnpAction(name = "AddNetworkShare")
    void addNetworkShare(@UpnpInputArgument(name = "name") String name,
                         @UpnpInputArgument(name = "path") String path,
                         @UpnpInputArgument(name = "user") String user,
                         @UpnpInputArgument(name = "pass") String pass);

    @UpnpAction(name = "GetTranscode", out = @UpnpOutputArgument(name = "transcode"))
    String getTranscode();

    @UpnpAction(name = "SetWirelessProfile")
    void setWirelessProfile(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken,
                            @UpnpInputArgument(name = "wirelessProfile", stateVariable = "CurrentWirelessProfile") String wirelessProfile);


    @UpnpAction(name = "GetAccessPointList", out = @UpnpOutputArgument(name = "accessPointList", stateVariable = "ARG_AccessPointList"))
    String GetAccessPointList(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken);

    @UpnpAction(name = "GetCurrentLanguage", out = @UpnpOutputArgument(name = "languageLocale", stateVariable = "CurrentLanguageLocale"))
    String GetCurrentLanguage();

    @UpnpAction(name = "GetSupportedLanguageList", out = @UpnpOutputArgument(name = "languageList", stateVariable = "ARG_SupportedLanguages"))
    String getSupportedLanguageList();

    @UpnpAction(name = "GetTimeZone", out = {@UpnpOutputArgument(name = "timeZone", stateVariable = "TimeZone"), @UpnpOutputArgument(name = "ianaName", stateVariable = "IANAName")})
    String getTimeZone();

    @UpnpAction(name = "RecallQuickSelect")
    void recallQuickSelect(@UpnpInputArgument(name = "QuickSelect") String quickSelect);

    @UpnpAction(name = "GetUpgradeProgress", out = @UpnpOutputArgument(name = "upgradeProgress", stateVariable = "UpgradeProgress"))
    String getUpgradeProgress();

    @UpnpAction(name = "GetWirelessState", out = @UpnpOutputArgument(name = "wirelessState", stateVariable = "WirelessState"))
    String getWirelessState();

    @UpnpAction(name = "SetConfigurationStatus")
    void SetConfigurationStatus(@UpnpInputArgument(name = "configurationStatus", stateVariable = "ConfigurationStatus") String configurationStatus);

    @UpnpAction(name = "GetFriendlyName", out = @UpnpOutputArgument(name = "friendlyName", stateVariable = "ARG_FriendlyName"))
    String GetFriendlyName();
}
