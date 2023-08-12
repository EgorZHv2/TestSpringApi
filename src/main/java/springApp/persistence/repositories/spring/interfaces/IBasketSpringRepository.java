package springApp.persistence.repositories.spring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import springApp.core.domain.entities.ProductsInBaskets;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IBasketSpringRepository extends IBaseSpringRepository<ProductsInBaskets, UUID> {
    @Async
    CompletableFuture<ProductsInBaskets> findByPk_User_IdAndPk_Product_Id(UUID userId, UUID productId);

    @Async
    CompletableFuture<List<ProductsInBaskets>> findAllByPk_User_Id(UUID userId);
}
