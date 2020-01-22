package bundang.exp.exp_request.domain;


import bundang.exp.user.User;
import bundang.exp.user.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpRequestComment extends DateAudit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne
    private ExpRequest expRequest;

    @ManyToOne
    private User user;
}
