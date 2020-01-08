package bundang.exp.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {

    @Email
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
