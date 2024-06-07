package bg.temps.backend.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("error")
    private String error;
    @JsonProperty("timestamp")
    private Instant timestamp;
    @JsonProperty("path")
    private String path;
    @JsonProperty("status")
    private int status;
}
