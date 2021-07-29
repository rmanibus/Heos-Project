package fr.lavachequicode.heos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SurroundSpeakerConfig {

    @JsonProperty("Front")
    Stereo front;
    @JsonProperty("Center")
    Center center;
    @JsonProperty("Subwoofer")
    Subwoofer subwoofer;
    @JsonProperty("Rear")
    Stereo rear;

    @Data
    public static class Stereo {
        Integer enabled;
        Integer crossover;
        @JsonProperty("Left")
        Speaker left;
        @JsonProperty("Right")
        Speaker right;
    }

    @Data
    public static class Center {
        Integer enabled;
        Integer crossover;
        @JsonProperty("Center")
        Speaker center;

    }

    @Data
    public static class Subwoofer {
        Integer enabled;
        Integer lowpass;
        Integer phase;
        @JsonProperty("Subwoofer")
        Speaker subwoofer;
    }
    @Data
    public static class Speaker {
        Integer distance;
        Integer level;
        Integer test_tone;
    }
}
