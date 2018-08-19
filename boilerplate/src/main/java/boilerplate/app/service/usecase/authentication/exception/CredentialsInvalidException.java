package boilerplate.app.service.usecase.authentication.exception;

import boilerplate.app.service.exception.CustomException;

public class CredentialsInvalidException extends CustomException {

    public CredentialsInvalidException() {
        super("credentials.invalid");
    }
}
