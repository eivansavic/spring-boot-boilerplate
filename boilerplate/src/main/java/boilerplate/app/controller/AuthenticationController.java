package boilerplate.app.controller;

import boilerplate.app.controller.dto.request.SignUpRequestDTO;
import boilerplate.app.service.usecase.authentication.SignUp;
import boilerplate.app.service.usecase.authentication.VerifyEmail;
import boilerplate.app.service.usecase.authentication.exception.UsernameExists;
import boilerplate.app.service.usecase.authentication.exception.VerificationTokenInvalidException;
import boilerplate.app.service.usecase.authentication.value.SignUpInfo;
import boilerplate.app.service.usecase.authentication.value.VerifyEmailInfo;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final SignUp signUp;
    private final VerifyEmail verifyEmail;

    @Autowired
    public AuthenticationController(SignUp signUp, VerifyEmail verifyEmail) {
        this.signUp = signUp;
        this.verifyEmail = verifyEmail;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequestDTO request) throws UsernameExists {
        logger.info("POST /authentication/sign-up - {}", request);
        signUp.signUp(new SignUpInfo(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.isEmailSubscribed(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getBirthday()));
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping(value = "/email/verification")
    public ResponseEntity verifyEmail(@RequestParam(value = "email") String email, @RequestParam(value = "token") String token) throws VerificationTokenInvalidException, UserNotFoundException {
        logger.info("POST /authentication/email/verification - {}", email, token);
        verifyEmail.verify(new VerifyEmailInfo(email, token));
        return new ResponseEntity<>(NO_CONTENT);
    }
}
