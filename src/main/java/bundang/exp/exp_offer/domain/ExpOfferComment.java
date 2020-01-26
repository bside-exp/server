package bundang.exp.exp_offer.domain;

import bundang.exp.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exp_offer_comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
