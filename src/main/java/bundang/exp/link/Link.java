package bundang.exp.link;

import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_request.domain.ExpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "link")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean requester;
    private Boolean provider;
    @ManyToOne
    private ExpRequest request;
    @ManyToOne
    private ExpOffer offer;
}
