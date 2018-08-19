package boilerplate.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyEmailRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("email")
    @NotBlank
    private String email;

    @JsonProperty("verification_token")
    @NotBlank
    private String verificationToken;
}
