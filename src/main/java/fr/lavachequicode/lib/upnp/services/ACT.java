package fr.lavachequicode.lib.upnp.services;

import fr.lavachequicode.lib.upnp.model.ACTCurrentState;
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
    ACTCurrentState getCurrentState();

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

    @UpnpAction(name = "GetActiveInterface", out = @UpnpOutputArgument(name = "networkConfigurationId", stateVariable = "ARG_NetworkConfigurationID"))
    String getActiveInterface();

    @UpnpAction(name = "ReMountNetworkShare")
    String ReMountNetworkShare(@UpnpInputArgument(name = "id", stateVariable = "id") String id);

    @UpnpAction(name = "CheckForFirmwareUpgrade")
    void CheckForFirmwareUpgrade();

    @UpnpAction(name = "GetNetworkShares", out = @UpnpOutputArgument(name = "NetworkShareConfig", stateVariable = "NetworkShareConfig"))
    String getNetworkShares();

    @UpnpAction(name = "RegisterUser")
    void RegisterUser(@UpnpInputArgument(name = "userInfo", stateVariable = "ARG_UserInfo") String userInfo);

    @UpnpAction(name = "ReleaseConfigurationToken")
    void ReleaseConfigurationToken(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken);

    @UpnpAction(name = "GetQuickSelectNames", out = @UpnpOutputArgument(name = "QuickSelectNames", stateVariable = "QuickSelectNames"))
    String getQuickSelectNames();

    @UpnpAction(name = "StopInvitation")
    void stopInvitation(@UpnpInputArgument(name = "InvitationConfiguration", stateVariable = "ARG_InvitationConfiguration") String invitationConfiguration);


    @UpnpAction(name = "SetWPSPinSSID")
    void setWPSPinSSID(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken,
                       @UpnpInputArgument(name = "wpsPinSSID", stateVariable = "ARG_WPSPinSSID") String wpsPinSSID);

    @UpnpAction(name = "SetTranscode")
    void setTranscode(@UpnpInputArgument(name = "transcode", stateVariable = "ARG_Transcode") String transcode);

    @UpnpAction(name = "GetWirelessProfile", out = @UpnpOutputArgument(name = "wirelessProfile", stateVariable = "CurrentWirelessProfile"))
    String getWirelessProfile();

    @UpnpAction(name = "SetQuickSelectNames")
    void SetQuickSelectNames(@UpnpInputArgument(name = "QuickSelectNames", stateVariable = "QuickSelectNames") String quickSelectNames);

    @UpnpAction(name = "DeleteNetworkShare")
    void deleteNetworkShare(@UpnpInputArgument(name = "id", stateVariable = "id") String id);

    @UpnpAction(name = "GetBluetoothStatus", out = @UpnpOutputArgument(name = "BTConfig"))
    String getBluetoothStatus();

    @UpnpAction(name = "CancelChanges")
    void CancelChanges(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken);

    @UpnpAction(name = "GetVolumeLimit", out = @UpnpOutputArgument(name = "VolumeLimit"))
    String getVolumeLimit();

    @UpnpAction(name = "SetDaylightSaving")
    void setDaylightSaving(@UpnpInputArgument(name = "daylightSaving", stateVariable = "DaylightSaving") String daylightSaving);

    @UpnpAction(name = "GetAudioConfig", out = @UpnpOutputArgument(name = "AudioConfig"))
    String getAudioConfig();

    @UpnpAction(name = "GetConfigurationStatus", out = @UpnpOutputArgument(name = "configurationStatus"))
    String getConfigurationStatus();

    @UpnpAction(name = "GetSessionId", out = @UpnpOutputArgument(name = "sessionId"))
    String getSessionId();

    @UpnpAction(name = "SetSurroundSpeakerConfig")
    void setSurroundSpeakerConfig(@UpnpInputArgument(name = "SurroundSpeakerConfig", stateVariable = "SurroundSpeakerConfig") String surroundSpeakerConfig);

    @UpnpAction(name = "StoreQuickSelect")
    void storeQuickSelect(@UpnpInputArgument(name = "QuickSelect", stateVariable = "QuickSelect") String quickSelect);

    @UpnpAction(name = "GetP2PMode", out = @UpnpOutputArgument(name = "P2PMode"))
    String getP2PMode();

    @UpnpAction(name = "GetWirelessStatus", out = @UpnpOutputArgument(name = "status"))
    String GetWirelessStatus();

    @UpnpAction(name = "ApplyChanges")
    void applyChanges(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken);

    @UpnpAction(name = "UpdateFirmware")
    void updateFirmware(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken);

    @UpnpAction(name = "SetVolumeLimit")
    void setVolumeLimit(@UpnpInputArgument(name = "VolumeLimit", stateVariable = "VolumeLimit") String volumeLimit);

    @UpnpAction(name = "GetDaylightSaving", out = @UpnpOutputArgument(name = "daylightSaving"))
    String getDaylightSaving();

    @UpnpAction(name = "GetLowLatencyConfig", out = @UpnpOutputArgument(name = "LowLatencyConfig"))
    String getLowLatencyConfig();

    @UpnpAction(name = "SetTimeZone")
    void SetTimeZone(@UpnpInputArgument(name = "timeZone", stateVariable = "TimeZone") String volumeLimit,
                     @UpnpInputArgument(name = "ianaName", stateVariable = "IANAName") String ianaName);


    @UpnpAction(name = "GetConfigurationToken", out = @UpnpOutputArgument(name = "configurationToken"))
    String getConfigurationToken();

    @UpnpAction(name = "GetNetworkConfigurationList", out = @UpnpOutputArgument(name = "networkConfigurations"))
    String getNetworkConfigurationList();

    @UpnpAction(name = "GetNetworkConfiguration", out = @UpnpOutputArgument(name = "networkConfiguration"))
    String getNetworkConfiguration(@UpnpInputArgument(name = "networkConfigurationId", stateVariable = "ARG_NetworkConfigurationID") String networkConfigurationId);

    @UpnpAction(name = "SetUpdateLevel")
    void setUpdateLevel(@UpnpInputArgument(name = "UpdateLevel", stateVariable = "UpdateLevel") String updateLevel);

    @UpnpAction(name = "SetUpdateAction")
    void setUpdateAction(@UpnpInputArgument(name = "UpdateLevel", stateVariable = "UpdateAction") String updateAction);

    @UpnpAction(name = "SetLowLatencyConfig")
    void setLowLatencyConfig(@UpnpInputArgument(name = "LowLatencyConfig", stateVariable = "LowLatencyConfig") String lowLatencyConfig);

    @UpnpAction(name = "SetAudioConfig")
    void setAudioConfig(@UpnpInputArgument(name = "AudioConfig", stateVariable = "AudioConfig") String audioConfig);

    @UpnpAction(name = "GetUpdateAction", out = @UpnpOutputArgument(name = "UpdateAction"))
    String getUpdateAction();

    @UpnpAction(name = "SetHEOSNetID")
    void setHEOSNetID(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken,
                      @UpnpInputArgument(name = "HEOSNetID", stateVariable = "ARG_HeosNetId") String heosNetID);

    @UpnpAction(name = "ReIndexNetworkShare")
    void ReIndexNetworkShare(@UpnpInputArgument(name = "id", stateVariable = "id") String id);

    @UpnpAction(name = "SetLEDConfig")
    void setLEDConfig(@UpnpInputArgument(name = "LEDConfig", stateVariable = "LEDConfig") String ledConfig);

    @UpnpAction(name = "StartWifiAp")
    void startWifiAp(@UpnpInputArgument(name = "WifiApConfiguration", stateVariable = "ARG_WifiApConfiguration") String wifiApConfiguration);

    @UpnpAction(name = "SetSessionId")
    void setSessionId(@UpnpInputArgument(name = "sessionId", stateVariable = "SessionId") String sessionId);

    @UpnpAction(name = "StartInvitation")
    void StartInvitation(@UpnpInputArgument(name = "InvitationConfiguration", stateVariable = "ARG_InvitationConfiguration") String invitationConfiguration);

    @UpnpAction(name = "StartInvitation")
    void StartInvitation(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken,
                         @UpnpInputArgument(name = "friendlyName", stateVariable = "ARG_FriendlyName") String friendlyName);

    @UpnpAction(name = "SetCurrentLanguage")
    void SetCurrentLanguage(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken,
                            @UpnpInputArgument(name = "languageLocale", stateVariable = "CurrentLanguageLocaleo") String languageLocale);

    @UpnpAction(name = "SetNetworkConfiguration")
    void setNetworkConfiguration(@UpnpInputArgument(name = "configurationToken", stateVariable = "ARG_ConfigurationToken") String configurationToken,
                                 @UpnpInputArgument(name = "networkConfiguration", stateVariable = "ARG_NetworkConfiguration") String networkConfiguration);

    @UpnpAction(name = "GetUpdateLevel", out = @UpnpOutputArgument(name = "UpdateLevel"))
    String GetUpdateLevel();

    @UpnpAction(name = "GetSurroundSpeakerConfig", out = @UpnpOutputArgument(name = "SurroundSpeakerConfig"))
    String getSurroundSpeakerConfig();

    @UpnpAction(name = "GetUpgradeStatus", out = @UpnpOutputArgument(name = "upgradeStatus"))
    String getUpgradeStatus();


    @UpnpAction(name = "SubmitDiagnostics")
    String SubmitDiagnostics(@UpnpInputArgument(name = "UserName", stateVariable = "ARG_UserName") String userName,
                             @UpnpInputArgument(name = "LogId", stateVariable = "ARG_LogId") String logId);

    @UpnpAction(name = "StopWifiAp")
    void StopWifiAp(@UpnpInputArgument(name = "WifiApConfiguration", stateVariable = "ARG_WifiApConfiguration") String wifiApConfiguration);

}
