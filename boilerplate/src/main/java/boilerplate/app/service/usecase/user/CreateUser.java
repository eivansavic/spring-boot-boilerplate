package boilerplate.app.service.usecase.user;

import boilerplate.app.model.User;
import boilerplate.app.model.enumeration.Role;
import boilerplate.app.repository.UserRepository;
import boilerplate.app.service.usecase.user.value.CreateUserInfo;
import boilerplate.app.model.User;
import boilerplate.app.model.enumeration.Role;
import boilerplate.app.repository.UserRepository;
import boilerplate.app.service.usecase.user.value.CreateUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CreateUser {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public CreateUser(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public User createUser(CreateUserInfo createUserInfo) {
        User user = new User();
        user.setFirstName(createUserInfo.getFirstName());
        user.setLastName(createUserInfo.getLastName());
        user.setEmail(createUserInfo.getEmail().toLowerCase());
        user.setEmailSubscribed(createUserInfo.isEmailSubscribed());
        user.setPhoneNumber(createUserInfo.getPhoneNumber());
        user.setHashPassword(passwordEncoder.encode(createUserInfo.getPassword()));
        user.setRole(Role.ROLE_CLIENT);
        user.setDeleted(false);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }
}
