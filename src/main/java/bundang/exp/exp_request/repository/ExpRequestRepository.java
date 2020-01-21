package bundang.exp.exp_request.repository;

import bundang.exp.exp_request.domain.ExpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpRequestRepository extends JpaRepository<ExpRequest, Long> {
}
