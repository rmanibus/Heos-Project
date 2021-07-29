package fr.lavachequicode.heos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuickSelectActive {
    @JsonProperty("QS1")
    Integer qs1;
    @JsonProperty("QS2")
    Integer qs2;
    @JsonProperty("QS3")
    Integer qs3;
    @JsonProperty("QS4")
    Integer qs4;
    @JsonProperty("QS5")
    Integer qs5;
    @JsonProperty("QS6")
    Integer qs6;
}
