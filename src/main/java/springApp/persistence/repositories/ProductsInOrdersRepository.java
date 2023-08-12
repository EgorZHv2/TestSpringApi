package springApp.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.IProductsInOrdersRepository;
import springApp.core.domain.entities.ProductsInOrders;
import springApp.persistence.repositories.spring.interfaces.IProductsInOrdersSpringRepository;

import java.security.PublicKey;
import java.util.UUID;

@Repository
public class ProductsInOrdersRepository extends BaseRepository<ProductsInOrders, UUID> implements IProductsInOrdersRepository {
    @Autowired
    public ProductsInOrdersRepository(IProductsInOrdersSpringRepository springRepository) {
        super(springRepository);
    }
}
