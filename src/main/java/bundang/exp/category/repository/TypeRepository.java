package bundang.exp.category.repository;

import bundang.exp.category.domain.Industry;
import bundang.exp.category.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findByNameIn(List<String> types);
}
