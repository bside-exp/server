package bundang.exp.exp_offer.repository;

import bundang.exp.exp_offer.domain.ExpOfferTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpOfferTagRepository extends JpaRepository<ExpOfferTag, Long> {
    List<ExpOfferTag> findAllByExpOfferId(Long expOfferId);

    void deleteAllByIdIn(List<Long> deleteTargetsIds);
}
