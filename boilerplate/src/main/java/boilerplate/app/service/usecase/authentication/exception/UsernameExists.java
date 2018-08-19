package boilerplate.app.service.usecase.authentication.exception;

import boilerplate.app.service.exception.CustomException;

public class UsernameExists extends CustomException {

    public UsernameExists() {
        super("email.exists");
    }
}
