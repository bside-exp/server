package bundang.exp.exp_request.domain;


import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.ExpOfferType;
import bundang.exp.category.domain.Industry;
import bundang.exp.user.User;
import bundang.exp.user.audit.DateAudit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpRequest extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne
    private Industry industry;

    @OneToOne
    private Duty duty;

    @ManyToMany
    private List<ExpOfferType> expOfferTypes;

    @Lob
    private String description;

    @OneToMany(mappedBy = "expRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExpRequestTag> tags;

    @ManyToOne
    private User user;
}
