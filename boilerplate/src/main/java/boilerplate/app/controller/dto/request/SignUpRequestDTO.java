package boilerplate.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("first_name")
    @NotBlank
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank
    private String lastName;

    @JsonProperty("email")
    @NotBlank
    @Email
    private String email;

    @JsonProperty("email_subscribed")
    @NotNull
    private boolean emailSubscribed;

    @JsonProperty("password")
    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    @JsonProperty("phone_number")
    @Size(min = 3, max = 20)
    private String phoneNumber;

    @JsonProperty("birthday")
    @NotNull
    private Date birthday;
}
