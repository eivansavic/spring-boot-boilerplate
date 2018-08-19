package boilerplate.app.service.usecase.authentication.exception;

import boilerplate.app.service.exception.CustomException;

public class PhoneNumberExists extends CustomException {

    public PhoneNumberExists() {
        super("phone.number.exists");
    }
}
