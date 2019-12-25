package bundang.exp.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Email;

@Setter
@Getter
public class JoinDto {

    @Email
    private String username;
    private String nickName;
    private String password;

}
