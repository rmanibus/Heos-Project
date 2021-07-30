package fr.lavachequicode.heos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Data
@JacksonXmlRootElement(namespace = "urn:schemas-upnp-org:metadata-1-0/ACT/", localName = "Event")
public class ACTCurrentState {

    static XmlMapper xmlMapper = new XmlMapper();

    {
        xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @JsonProperty("ActiveInterface")
    void unpackActiveInterface(Map<String, String> data) {
        activeInterface = data.get("val");
    }

    String activeInterface;

    @JsonProperty("FriendlyName")
    void unpackFriendlyName(Map<String, String> data) {
        friendlyName = data.get("val");
    }
    String friendlyName;

    @JsonProperty("HEOSNetId")
    void unpackHeosNetId(Map<String, String> data) {
        heosNetId = data.get("val");
    }

    String heosNetId;

    @JsonProperty("LastDiscoveredDevice")
    void unpackLastDiscoveredDevice(Map<String, String> data) {
        lastDiscoveredDevice = data.get("val");
    }

    String lastDiscoveredDevice;

    @JsonProperty("P2PMode")
    void unpackP2PMode(Map<String, String> data) {
        p2pMode = data.get("val");
    }

    String p2pMode;

    @JsonProperty("Transcode")
    void unpackTranscode(Map<String, String> data) {
        transcode = data.get("val");
    }

    String transcode;

    @JsonProperty("AudioConfig")
    void unpackAudioConfig(Map<String, String> data) throws JsonProcessingException {
        audioConfig = readNested(data.get("val"), AudioConfig.class);
    }

    AudioConfig audioConfig;

    @JsonProperty("BTConfig")
    void unpackBtConfig(Map<String, String> data) throws JsonProcessingException {
        btConfig = readNested(data.get("val"), BluetoothStatus.class);
    }

    BluetoothStatus btConfig;

    @JsonProperty("ConfigurationStatus")
    void unpackConfigurationStatus(Map<String, String> data) {
        configurationStatus = data.get("val");
    }

    String configurationStatus;

    @JsonProperty("UpgradeComponentInstallProgress")
    void unpackUpgradeComponentInstallProgress(Map<String, String> data) {
        upgradeComponentInstallProgress = data.get("val");
    }

    String upgradeComponentInstallProgress;

    @JsonProperty("CurrentLanguageLocale")
    void unpackCurrentLanguageLocale(Map<String, String> data) {
        currentLanguageLocale = data.get("val");
    }

    String currentLanguageLocale;

    @JsonProperty("CurrentWirelessProfile")
    void unpackCurrentWirelessProfile(Map<String, String> data) throws JsonProcessingException {
        currentWirelessProfile = readNested(data.get("val"), WirelessProfile.class);
    }
    WirelessProfile currentWirelessProfile;

    @JsonProperty("DaylightSaving")
    void unpackDaylightSaving(Map<String, String> data) {
        daylightSaving = data.get("val");
    }

    String daylightSaving;

    @JsonProperty("IANAName")
    void unpackIANAName(Map<String, String> data) {
        ianaName = data.get("val");
    }

    String ianaName;

    @JsonProperty("LEDConfig")
    void unpackLedConfig(Map<String, String> data) throws JsonProcessingException {
        ledConfig = readNested(data.get("val"), LEDConfig.class);
    }

    LEDConfig ledConfig;

    @JsonProperty("LowLatencyConfig")
    void unpackLowLatencyConfig(Map<String, String> data) throws JsonProcessingException {
        lowLatencyConfig = readNested(data.get("val"), LowLatencyConfig.class);
    }

    LowLatencyConfig lowLatencyConfig;

    @JsonProperty("NetworkConfigurationList")
    void unpackNetworkConfigurationList(Map<String, String> data) throws JsonProcessingException {
        networkConfigurationList = readNested(data.get("val"), ListNetworkConfigurations.class);
    }

    ListNetworkConfigurations networkConfigurationList;

    @JsonProperty("NetworkShareConfig")
    void unpackNetworkShareConfig(Map<String, String> data) throws JsonProcessingException {
        networkShareConfig = data.get("val");
    }

    String networkShareConfig;

    @JsonProperty("QuickSelectActive")
    void unpackQuickSelectActive(Map<String, String> data) throws JsonProcessingException {
        quickSelectActive = readNested(data.get("val"), QuickSelectActive.class);

    }

    QuickSelectActive quickSelectActive;

    @JsonProperty("QuickSelectNames")
    void unpackQuickSelectNames(Map<String, String> data) throws JsonProcessingException {
        quickSelectNames = readNested(data.get("val"), QuickSelectNames.class);
    }

    QuickSelectNames quickSelectNames;

    @JsonProperty("SessionId")
    void unpackSessionId(Map<String, String> data) {
        sessionId = data.get("val");
    }
    String sessionId;

    @JsonProperty("SurroundSpeakerConfig")
    void unpackSurroundSpeakerConfig(Map<String, String> data) throws JsonProcessingException {
        surroundSpeakerConfig = readNested(data.get("val"), SurroundSpeakerConfig.class);
    }

    SurroundSpeakerConfig surroundSpeakerConfig;

    @JsonProperty("TimeZone")
    void TimeZone(Map<String, String> data) {
        timeZone = data.get("val");
    }

    String timeZone;

    @JsonProperty("TvConfig")
    void unpackTvConfig(Map<String, String> data) throws JsonProcessingException {
        tvConfig = readNested(data.get("val"), TvConfig.class);
    }

    TvConfig tvConfig;
    @JsonProperty("UpdateAction")
    void unpackUpdateAction(Map<String, String> data) {
        updateAction = data.get("val");
    }
    String updateAction;
    @JsonProperty("UpdateLevel")
    void unpackUpdateLevel(Map<String, String> data) {
        updateLevel = data.get("val");
    }
    String updateLevel;
    @JsonProperty("UpgradeProgress")
    void unpackUpgradeProgress(Map<String, String> data) {
        upgradeProgress = data.get("val");
    }
    String upgradeProgress;

    @JsonProperty("UpgradeStatus")
    void unpackUpgradeStatus(Map<String, String> data) {
        upgradeStatus = data.get("val");
    }
    String upgradeStatus;

    @JsonProperty("VolumeLimit")
    void unpackVolumeLimit(Map<String, String> data) {
        volumeLimit = data.get("val");
    }
    String volumeLimit;

    @JsonProperty("WifiApSsid")
    void unpackWifiApSsid(Map<String, String> data) {
        wifiApSsid = data.get("val");
    }
    String wifiApSsid;

    @JsonProperty("WirelessState")
    void unpackWirelessState(Map<String, String> data) {
        wirelessState = data.get("val");
    }
    String wirelessState;

    @JsonProperty("devicePlacement")
    void unpackDevicePlacement(Map<String, String> data) {
        devicePlacement = data.get("val");
    }
    String devicePlacement;

    @Data
    public static class Content {
        @JacksonXmlProperty(isAttribute = true, localName = "val")
        String value;
    }

    static <T> T readNested(String value, Class<T> tClass) throws JsonProcessingException {

        if (value == null || value.length() == 0) {
            return null;
        }
        return (T) xmlMapper.readValue(value, tClass);
    }
}
