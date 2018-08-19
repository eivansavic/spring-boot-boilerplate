package boilerplate.app.service.usecase.authentication.exception;

import boilerplate.app.service.exception.CustomException;
import boilerplate.app.service.exception.CustomException;

public class VerificationCodeInvalidException extends CustomException {

    public VerificationCodeInvalidException() {
        super("verification.code.invalid");
    }
}
