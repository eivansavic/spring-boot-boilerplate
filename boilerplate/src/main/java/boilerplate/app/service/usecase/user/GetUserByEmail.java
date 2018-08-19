package boilerplate.app.service.usecase.user;

import boilerplate.app.model.User;
import boilerplate.app.repository.UserRepository;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;
import boilerplate.app.model.User;
import boilerplate.app.repository.UserRepository;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByEmail {

    private final UserRepository userRepository;

    @Autowired
    public GetUserByEmail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }
}
