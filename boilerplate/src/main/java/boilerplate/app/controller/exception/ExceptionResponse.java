package boilerplate.app.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
class ExceptionResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("status_text")
    private String statusText;

    @JsonProperty("localized_error_message")
    private String localizedErrorMessage;

    @JsonProperty("error_description")
    private String errorDescription;

    private List<String> data;

    ExceptionResponse(String localizedErrorMessage, String errorDescription, HttpStatus status) {
        this();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.localizedErrorMessage = localizedErrorMessage;
        this.errorDescription = errorDescription;
    }

    void setErrors(List<String> data) {
        this.data = data;
    }
}
