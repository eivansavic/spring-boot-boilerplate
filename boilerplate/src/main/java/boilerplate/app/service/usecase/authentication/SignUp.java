package boilerplate.app.service.usecase.authentication;

import boilerplate.app.service.usecase.authentication.value.SignUpInfo;
import boilerplate.app.model.User;
import boilerplate.app.repository.UserRepository;
import boilerplate.app.service.usecase.authentication.exception.UsernameExists;
import boilerplate.app.service.usecase.authentication.value.SendVerificationMailInfo;
import boilerplate.app.service.usecase.authentication.value.SignUpInfo;
import boilerplate.app.service.usecase.user.CreateUser;
import boilerplate.app.service.usecase.user.value.CreateUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUp {

    private final UserRepository userRepository;
    private final CreateUser createUser;
    private final SendVerificationMail sendVerificationMail;
    private final CreateVerificationToken createVerificationToken;

    @Autowired
    public SignUp(UserRepository userRepository, CreateUser createUser, SendVerificationMail sendVerificationMail, CreateVerificationToken createVerificationToken) {
        this.userRepository = userRepository;
        this.createUser = createUser;
        this.sendVerificationMail = sendVerificationMail;
        this.createVerificationToken = createVerificationToken;
    }

    public void signUp(SignUpInfo signUpInfo) throws UsernameExists {
        checkUserExistsByEmail(signUpInfo.getEmail());

        User user = createUser.createUser(new CreateUserInfo(
                signUpInfo.getFirstName(),
                signUpInfo.getLastName(),
                signUpInfo.getEmail(),
                signUpInfo.isEmailSubscribed(),
                signUpInfo.getPassword(),
                signUpInfo.getPhoneNumber(),
                signUpInfo.getBirthday()));

        final String token = createVerificationToken.create(user);

        sendVerificationMail.send(new SendVerificationMailInfo(signUpInfo.getEmail(), token));
    }

    private void checkUserExistsByEmail(String email) throws UsernameExists {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UsernameExists();
        }
    }
}
