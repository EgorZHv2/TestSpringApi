package springApp.core.application.interfaces.repositories;

import springApp.core.domain.entities.ProductsInBaskets;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IBasketRepository extends IBaseRepository<ProductsInBaskets, UUID> {
    CompletableFuture<List<ProductsInBaskets>> getAllByUserId(UUID userId);
}
