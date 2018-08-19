package boilerplate.app.controller.exception;

import boilerplate.app.service.exception.CustomException;
import boilerplate.app.service.usecase.authentication.exception.*;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;
import boilerplate.app.service.exception.CustomException;
import boilerplate.app.service.usecase.authentication.exception.*;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final static String INTERNAL_SERVER_ERROR = "Oops, something went wrong.";
    private final static String INVALID_INPUT = "Invalid input.";
    private final static String ACCESS_DENIED = "Access is denied.";

    @ExceptionHandler(value = {CredentialsInvalidException.class, UsernameExists.class, PhoneNumberExists.class})
    public ResponseEntity handleConflictException(CustomException e) {
        return buildResponseEntity(new ExceptionResponse(e.getLocalizedErrorMessage(), e.getErrorDescription(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity handleNotFoundException(CustomException e) {
        return buildResponseEntity(new ExceptionResponse(e.getLocalizedErrorMessage(), e.getErrorDescription(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = {VerificationCodeInvalidException.class, VerificationTokenInvalidException.class})
    public ResponseEntity handleUnauthorizedException(Exception e) {
        logger.error("Handling access denied error - {}", e.getMessage(), e);
        return buildResponseEntity(new ExceptionResponse(ACCESS_DENIED, ACCESS_DENIED, HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(Exception e) {
        logger.error("Handling access denied error - {}", e.getMessage(), e);
        return buildResponseEntity(new ExceptionResponse(ACCESS_DENIED, ACCESS_DENIED, HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception e) {
        logger.error("Handling unknown error - {}", e.getMessage(), e);
        return buildResponseEntity(new ExceptionResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handling method argument not valid error - {}", ex.getMessage(), ex);
        ExceptionResponse response = new ExceptionResponse(INVALID_INPUT, INVALID_INPUT, HttpStatus.BAD_REQUEST);
        response.setErrors(fromBindingErrors(ex.getBindingResult()));
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        logger.error("Handling error - {}", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(exceptionResponse.getStatus()));
    }

    private List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<>();
        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(((FieldError) objectError).getField() + " " + objectError.getDefaultMessage());
        }
        return validErrors;
    }
}
