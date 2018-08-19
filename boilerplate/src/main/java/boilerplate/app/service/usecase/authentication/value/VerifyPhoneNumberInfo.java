package boilerplate.app.service.usecase.authentication.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VerifyPhoneNumberInfo {

    private String phoneNumber;

    private String code;
}
