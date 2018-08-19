package boilerplate.app.controller;

import boilerplate.app.model.User;
import boilerplate.app.service.usecase.user.GetUserById;
import boilerplate.app.service.usecase.user.exception.UserNotFoundException;
import boilerplate.app.controller.dto.response.UserResponseDTO;
import boilerplate.app.service.usecase.user.GetUserByEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final GetUserById getUserById;
    private final GetUserByEmail getUserByEmail;

    @Autowired
    public UserController(GetUserById getUserById, GetUserByEmail getUserByEmail) {
        this.getUserById = getUserById;
        this.getUserByEmail = getUserByEmail;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserResponseDTO> getMe(Principal principal) throws UserNotFoundException {
        logger.info("GET/api/me");
        return new ResponseEntity<>(convertToUserResponse(getUserByEmail.getUserByEmail(principal.getName())), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id, Principal principal) throws UserNotFoundException {
        logger.info("GET/api/users/{}", id);
        return new ResponseEntity<>(convertToUserResponse(getUserById.getUserById(id)), HttpStatus.OK);
    }

    private UserResponseDTO convertToUserResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}
