package bundang.exp.exp_offer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exp_offer_tag")
@Builder
@Data
@AllArgsConstructor
public class ExpOfferTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    @JsonIgnore
    @ManyToOne
    private ExpOffer expOffer;
}
