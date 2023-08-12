package springApp.core.application.interfaces.repositories;

import springApp.core.domain.entities.Product;

import java.util.UUID;

public interface IProductRepository extends IBaseRepository<Product, UUID> {
}
