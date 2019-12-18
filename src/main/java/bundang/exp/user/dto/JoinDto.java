package bundang.exp.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
public class JoinDto {

    private String username;
    private String nickName;
    private String password;

}
