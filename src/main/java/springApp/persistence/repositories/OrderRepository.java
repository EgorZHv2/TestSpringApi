package springApp.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.IBaseRepository;
import springApp.core.application.interfaces.repositories.IOrderRepository;
import springApp.core.domain.entities.Order;
import springApp.persistence.repositories.spring.interfaces.IOrderSpringRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class OrderRepository extends BaseRepository<Order, UUID> implements IOrderRepository {
    @Autowired
    public OrderRepository(IOrderSpringRepository orderSpringRepository){
        super(orderSpringRepository);
    }
    @Async
    public CompletableFuture<List<Order>> getAllByUserId(UUID id){
      return  ((IOrderSpringRepository) springRepository).findAllByUserId(id);
    }
}
