package boilerplate.app.controller.dto.response;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
