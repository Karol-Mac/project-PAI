package net.enjoy.springboot.registrationlogin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Size(min = 3, max = 30, message = "username should have at least 3 characters")
    private String username;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    message = "must have at least: 1 small letter, 1 uppercase letter, 1 number, " +
            "1 special character & at least 8 characters (total) ")
    private String password;
}