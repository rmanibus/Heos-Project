package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class TvConfig {

    String input;
    //String connectedInputs;
    Integer hdmiVolume;
    String hdmiConnection;
    Integer remoteVolume;
    Integer autoPlay;
    Integer irFlasherFeedback;
    Integer allowZoning;
    DialogueEnhance dialogueEnhance;
    Integer audioDelay;
    String syncMode;
    String bilingualMode;
    Integer irCodeVolPlus;
    Integer irCodeVolMinus;
    Integer irCodeMute;
    Integer irCodeAux;
    Integer irCodeLine;
    Integer irCodeAnalog;
    Integer irCodeAnalog1;
    Integer irCodeAnalog2;
    Integer irCodeCd;
    Integer irCodeRecorder;
    Integer irCodeCoaxial;
    Integer irCodeOptical;
    Integer irCodeOptical1;
    Integer irCodeOptical2;
    Integer irCodeHdmi;
    Integer irCodeHdmiArc;
    Integer irCodeHdmi1;
    Integer irCodeHdmi2;
    Integer irCodeHdmi3;
    Integer irCodeHdmi4;
    Integer irCodeQuickSel1;
    Integer irCodeQuickSel2;
    Integer irCodeQuickSel3;
    Integer irCodeQuickSel4;
    Integer irCodeQuickSel5;
    Integer irCodeQuickSel6;
    Integer irCodePowerToggle;
    Integer irCodePowerOn;
    Integer irCodePowerOff;
    Integer irCodeTv;
    Integer irCodeBluetooth;
    Integer irCodeSubwooferPlus;
    Integer irCodeSubwooferMinus;
    Integer irCodeBassPlus;
    Integer irCodeBassMinus;
    Integer irCodeNightMode;
    Integer irCodeDialogue;
    Integer irCodeSoundMovie;
    Integer irCodeSoundMusic;
    Integer irCodeSoundPure;

    DtsDialogControl dtsDialogControl;

    @Data
    public static class DtsDialogControl {
        Integer level;
        Integer enabled;
        Integer max;
    }

    @Data
    public static class DialogueEnhance {
        Integer level;
        Integer enabled;
        @JacksonXmlText
        String value;
    }
}
