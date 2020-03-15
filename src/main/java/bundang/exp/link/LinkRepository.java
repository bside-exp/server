package bundang.exp.link;

import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_request.domain.ExpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByRequest(ExpRequest request);

    List<Link> findByOffer(ExpOffer offer);

    Optional<Link> findByRequestAndOffer(ExpRequest request, ExpOffer offer);
}
