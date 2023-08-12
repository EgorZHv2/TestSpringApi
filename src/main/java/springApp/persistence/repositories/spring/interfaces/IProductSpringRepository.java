package springApp.persistence.repositories.spring.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.core.domain.entities.Product;

import java.util.UUID;

public interface IProductSpringRepository extends IBaseSpringRepository<Product,UUID> {

}
