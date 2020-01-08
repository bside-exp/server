package bundang.exp.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class JoinDto {

    @Email
    @NotBlank
    private String username;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;

}
