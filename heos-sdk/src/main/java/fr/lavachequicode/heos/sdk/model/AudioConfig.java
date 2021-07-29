package fr.lavachequicode.heos.sdk.model;

import lombok.Data;

@Data
public class AudioConfig {

    String highpass;
    String lowpass;
    String subwooferEnable;
    String outputMode;
    String ampBridged;
    String soundMode;
    String impedance;
    String ampPower;
    String availableSoundModes;
    String sourceDirect;
    String bassBoost;
    String speakerOption;
}
