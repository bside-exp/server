package bundang.exp.exp_offer.domain;

import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.ExpOfferType;
import bundang.exp.category.domain.Industry;
import bundang.exp.user.User;
import bundang.exp.user.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exp_offer")
@Data
@AllArgsConstructor
@Builder
public class ExpOffer extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contact;
    private String email;
    private String firmName;
    private Integer expDuration;

    @ManyToOne
    private User user;

    @ManyToOne
    private Industry industry;

    @ManyToOne
    private Duty duty;

    @ManyToMany
    private List<ExpOfferType> expOfferTypes;

    @Lob
    private String description;

    @OneToMany(mappedBy = "expOffer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExpOfferTag> expOfferTags;
}
