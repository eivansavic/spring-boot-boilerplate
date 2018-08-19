package boilerplate.app.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
