package springApp.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.IProductRepository;
import springApp.core.domain.entities.Product;
import springApp.persistence.repositories.spring.interfaces.IProductSpringRepository;

import java.util.UUID;
@Repository
public class ProductRepository extends BaseRepository<Product, UUID> implements IProductRepository {
    @Autowired
    public ProductRepository(IProductSpringRepository productSpringRepository){
        super(productSpringRepository);
    }
}
