package springApp.persistence.repositories.spring.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.core.domain.entities.Category;

import java.util.UUID;

public interface ICategorySpringRepository extends IBaseSpringRepository<Category,UUID> {
}
