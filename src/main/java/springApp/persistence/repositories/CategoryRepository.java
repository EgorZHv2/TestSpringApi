package springApp.persistence.repositories;

import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.ICategoryRepository;
import springApp.core.domain.entities.Category;
import springApp.persistence.repositories.spring.interfaces.ICategorySpringRepository;

import java.util.UUID;

@Repository
public class CategoryRepository extends BaseRepository<Category, UUID> implements ICategoryRepository {
    public CategoryRepository(ICategorySpringRepository categorySpringRepository) {
        super(categorySpringRepository);
    }

}
