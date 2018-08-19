package boilerplate.app.service.usecase.user.exception;

import boilerplate.app.service.exception.CustomException;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super("user.not.found");
    }
}
