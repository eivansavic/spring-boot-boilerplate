package boilerplate.app.service.usecase.user.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@AllArgsConstructor
@Getter
public class CreateUserInfo {

    private String firstName;

    private String lastName;

    private String email;

    private boolean emailSubscribed;

    private String password;

    private String phoneNumber;

    private Date birthday;
}
