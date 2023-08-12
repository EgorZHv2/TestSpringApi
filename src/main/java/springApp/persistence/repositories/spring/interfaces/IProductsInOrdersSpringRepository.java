package springApp.persistence.repositories.spring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import springApp.core.domain.entities.ProductsInOrders;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IProductsInOrdersSpringRepository extends IBaseSpringRepository<ProductsInOrders,UUID> {
    @Async
    CompletableFuture<List<ProductsInOrders>> findAllByPk_Order_Id(UUID orderId);
}
