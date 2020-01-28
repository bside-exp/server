package bundang.exp.category.repository;

import bundang.exp.category.domain.ExpOfferType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpOfferTypeRepository extends JpaRepository<ExpOfferType, Long> {
    List<ExpOfferType> findByNameIn(List<String> types);
}
