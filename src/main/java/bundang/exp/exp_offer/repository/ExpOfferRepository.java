package bundang.exp.exp_offer.repository;

import bundang.exp.exp_offer.domain.ExpOffer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpOfferRepository extends JpaRepository<ExpOffer, Long> {
    List<ExpOffer> findTop3ByOrderByIdDesc();
}
