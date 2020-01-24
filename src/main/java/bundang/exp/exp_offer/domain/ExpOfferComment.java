package bundang.exp.exp_offer.domain;

import bundang.exp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "exp_offer_comment")
public class ExpOfferComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private ExpOffer expOffer;
}
