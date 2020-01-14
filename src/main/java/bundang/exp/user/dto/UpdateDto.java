package bundang.exp.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateDto {
    @Email
    @NotBlank
    private String username;
    private String nickname;
    private String password;
}
