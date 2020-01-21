package bundang.exp.exp_request.repository;

import bundang.exp.exp_request.domain.ExpRequestTag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;

public interface ExpRequestTagRepository extends JpaRepository<ExpRequestTag, Long> {
    @Query("from ExpRequestTag e inner join fetch e.expRequest where e.expRequest.id = :id")
    List<ExpRequestTag> findByExpRequestId(@Param("id") Long id);
}
