package bundang.exp.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class LoginDto {

    @Email
    private String username;
    private String password;

}
