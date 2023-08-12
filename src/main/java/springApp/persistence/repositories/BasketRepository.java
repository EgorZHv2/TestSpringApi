package springApp.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springApp.core.application.interfaces.repositories.IBasketRepository;
import springApp.core.domain.entities.ProductsInBaskets;
import springApp.persistence.repositories.spring.interfaces.IBasketSpringRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class BasketRepository extends BaseRepository<ProductsInBaskets, UUID>  implements IBasketRepository {

    @Autowired
    public BasketRepository(IBasketSpringRepository basketSpringRepository)
    {
        super(basketSpringRepository);
    }
    public CompletableFuture<List<ProductsInBaskets>> getAllByUserId(UUID userId){
        return ((IBasketSpringRepository)springRepository).findAllByPk_User_Id(userId);
    }


}
