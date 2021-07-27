package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Data
@JacksonXmlRootElement(namespace = "urn:schemas-upnp-org:metadata-1-0/ACT/", localName = "Event")
@Slf4j
public class ACTCurrentState {

    static XmlMapper xmlMapper = new XmlMapper();

    {
        xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @JsonProperty("ActiveInterface")
    Content activeInterface;
    @JsonProperty("FriendlyName")
    Content friendlyName;
    @JsonProperty("HEOSNetId")
    Content heosNetId;
    @JsonProperty("LastDiscoveredDevice")
    Content lastDiscoveredDevice;
    @JsonProperty("P2PMode")
    Content p2pMode;
    @JsonProperty("Transcode")
    Content transcode;

    @JsonProperty("AudioConfig")
    void unpackAudioConfig(Map<String, String> data) throws JsonProcessingException {
        audioConfig = xmlMapper.readValue(data.get("val"), AudioConfig.class);
    }

    AudioConfig audioConfig;


    @JsonProperty("BTConfig")
    void unpackBtConfig(Map<String, String> data) throws JsonProcessingException {
        btConfig = xmlMapper.readValue(data.get("val"), BluetoothStatus.class);
    }

    BluetoothStatus btConfig;
    @JsonProperty("ConfigurationStatus")
    Content configurationStatus;
    @JsonProperty("UpgradeComponentInstallProgress")
    Content upgradeComponentInstallProgress;
    @JsonProperty("CurrentLanguageLocale")
    Content currentLanguageLocale;

    @JsonProperty("CurrentWirelessProfile")
    void unpackWirelessState(Map<String, String> data) throws JsonProcessingException {
        currentWirelessProfile = xmlMapper.readValue(data.get("val"), WirelessProfile.class);
    }

    WirelessProfile currentWirelessProfile;

    @JsonProperty("DaylightSaving")
    Content daylightSaving;
    @JsonProperty("IANAName")
    Content ianaName;

    @JsonProperty("LEDConfig")
    void unpackLedConfig(Map<String, String> data) throws JsonProcessingException {
        ledConfig = xmlMapper.readValue(data.get("val"), LEDConfig.class);
    }

    LEDConfig ledConfig;

    @JsonProperty("LowLatencyConfig")
    String lowLatencyConfig;

    @JsonProperty("NetworkConfigurationList")
    void unpackNetworkConfigurationList(Map<String, String> data) throws JsonProcessingException {
        networkConfigurationList = xmlMapper.readValue(data.get("val"), ListNetworkConfigurations.class);
    }

    ListNetworkConfigurations networkConfigurationList;

    @JsonProperty("NetworkShareConfig")
    Content networkShareConfig;

    @JsonProperty("QuickSelectActive")
    void unpackQuickSelectActive(Map<String, String> data) throws JsonProcessingException {
        quickSelectActive = xmlMapper.readValue(data.get("val"), QuickSelectActive.class);
    }

    QuickSelectActive quickSelectActive;

    @JsonProperty("QuickSelectNames")
    void unpackQuickSelectNames(Map<String, String> data) throws JsonProcessingException {
        quickSelectNames = xmlMapper.readValue(data.get("val"), QuickSelectNames.class);
    }

    QuickSelectNames quickSelectNames;
    @JsonProperty("SessionId")
    Content sessionId;

    @JsonProperty("SurroundSpeakerConfig")
    void unpackSurroundSpeakerConfig(Map<String, String> data) throws JsonProcessingException {
        surroundSpeakerConfig = xmlMapper.readValue(data.get("val"), SurroundSpeakerConfig.class);
    }

    SurroundSpeakerConfig surroundSpeakerConfig;
    @JsonProperty("TimeZone")
    Content timeZone;

    @JsonProperty("TvConfig")
    void unpackTvConfig(Map<String, String> data) throws JsonProcessingException {
        tvConfig = xmlMapper.readValue(data.get("val"), TvConfig.class);
    }

    TvConfig tvConfig;
    @JsonProperty("UpdateAction")
    Content updateAction;
    @JsonProperty("UpdateLevel")
    Content updateLevel;
    @JsonProperty("UpgradeProgress")
    Content upgradeProgress;
    @JsonProperty("UpgradeStatus")
    Content upgradeStatus;
    @JsonProperty("VolumeLimit")
    Content volumeLimit;
    @JsonProperty("WifiApSsid")
    Content wifiApSsid;

    @JsonProperty("WirelessState")
    Content wirelessState;

    @JsonProperty("devicePlacement")
    Content devicePlacement;

    @Data
    public static class Content {
        @JacksonXmlProperty(
                isAttribute = true, localName = "val")
        String value;
    }
}
