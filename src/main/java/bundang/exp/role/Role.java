package bundang.exp.role;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles name;

}

