package boilerplate.app.service.usecase.authentication.exception;

import boilerplate.app.service.exception.CustomException;

public class VerificationTokenInvalidException extends CustomException {

    public VerificationTokenInvalidException() {
        super("verification.token.invalid");
    }
}
