package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuickSelectNames {
    @JsonProperty("QS1")
    String qs1;
    @JsonProperty("QS2")
    String qs2;
    @JsonProperty("QS3")
    String qs3;
    @JsonProperty("QS4")
    String qs4;
    @JsonProperty("QS5")
    String qs5;
    @JsonProperty("QS6")
    String qs6;
}
