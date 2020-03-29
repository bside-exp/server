package bundang.exp.exp_request.repository;

import bundang.exp.exp_request.domain.ExpRequest;
import bundang.exp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpRequestRepository extends JpaRepository<ExpRequest, Long> {
    List<ExpRequest> findByUser(User user);
}
