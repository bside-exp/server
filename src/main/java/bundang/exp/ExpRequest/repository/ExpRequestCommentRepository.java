package bundang.exp.ExpRequest.repository;

import bundang.exp.ExpRequest.damian.ExpRequest;
import bundang.exp.ExpRequest.damian.ExpRequestComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpRequestCommentRepository extends JpaRepository<ExpRequestComment, Long> {
}
