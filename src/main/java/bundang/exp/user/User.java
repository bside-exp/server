package bundang.exp.user;

import bundang.exp.role.Role;
import bundang.exp.user.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String username;
    private String nickName;

    @JsonIgnore
    private String password;

    @OneToMany
    private List<Role> roles = new ArrayList<>();


}
