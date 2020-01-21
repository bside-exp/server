package bundang.exp.category.repository;

import bundang.exp.category.domain.Duty;
import bundang.exp.category.domain.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    Optional<Industry> findByName(String industry);
}
