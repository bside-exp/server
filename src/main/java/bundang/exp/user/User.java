package bundang.exp.user;

import bundang.exp.role.Role;
import bundang.exp.user.audit.DateAudit;
import bundang.exp.user.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateAudit {

    public static User create(UserPrincipal userPrincipal) {
        User user = new User();
        user.setId(userPrincipal.getId());
        user.setUsername(userPrincipal.getUsername());
        user.setNickName(userPrincipal.getNickname());
        user.setPassword(userPrincipal.getPassword());

        return user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String username;
    private String nickName;

    @JsonIgnore
    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();


}
