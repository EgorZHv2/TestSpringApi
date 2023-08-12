package springApp.persistence.repositories.spring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import springApp.core.domain.entities.Order;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IOrderSpringRepository extends IBaseSpringRepository<Order,UUID> {
    @Async
    CompletableFuture<List<Order>> findAllByUserId(UUID userId);
}
