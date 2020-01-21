package bundang.exp.exp_request.repository;

import bundang.exp.exp_request.domain.ExpRequestComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpRequestCommentRepository extends JpaRepository<ExpRequestComment, Long> {
}
