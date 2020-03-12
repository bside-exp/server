package bundang.exp.link;

import bundang.exp.exp_offer.domain.ExpOffer;
import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByRequester(User requester);

    Optional<Link> findByProvider(User provider);

    Optional<Link> findByRequest(ExpRequest request);

    Optional<Link> findByOffer(ExpOffer offer);

    Optional<Link> findByRequestAndOffer(ExpRequest request, ExpOffer offer);
}
