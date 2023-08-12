package springApp.core.application.interfaces.repositories;

import springApp.core.domain.entities.Order;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IOrderRepository extends IBaseRepository<Order, UUID> {
    CompletableFuture<List<Order>> getAllByUserId(UUID id);
}
