package bundang.exp.category.repository;

import bundang.exp.category.domain.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DutyRepository extends JpaRepository<Duty, Long> {
    Optional<Duty> findByName(String duty);
}
