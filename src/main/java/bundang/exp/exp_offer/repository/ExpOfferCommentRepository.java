package bundang.exp.exp_offer.repository;

import bundang.exp.exp_offer.domain.ExpOfferComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpOfferCommentRepository extends JpaRepository<ExpOfferComment, Long> {
    Page<ExpOfferComment> findAllByExpOfferId(Long expOfferId, Pageable pageable);
}
