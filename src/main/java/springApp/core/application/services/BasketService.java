package springApp.core.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import springApp.core.application.dto.basket.BasketCreateDTO;
import springApp.core.application.dto.product.ProductOutputDTO;
import springApp.core.application.exceptions.ProductNotFoundException;
import springApp.core.application.exceptions.UserNotFoundException;
import springApp.core.application.interfaces.repositories.IBasketRepository;
import springApp.core.application.interfaces.repositories.IProductRepository;
import springApp.core.application.interfaces.repositories.IUserRepository;
import springApp.core.application.interfaces.services.IBasketService;
import springApp.core.domain.entities.ProductsInBaskets;
import springApp.core.domain.entities.ProductsInBasketsId;
import springApp.infrastructure.security.models.UserDetailsImpl;
import springApp.persistence.repositories.spring.interfaces.IBasketSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IProductSpringRepository;
import springApp.persistence.repositories.spring.interfaces.IUserSpringRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class BasketService implements IBasketService {
    @Autowired
    private IBasketRepository basketRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    @Async
    public void addProductToBasket(BasketCreateDTO dto) throws InterruptedException, ExecutionException {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var entity = new ProductsInBaskets();
        var user = userRepository.getById(principal.getId()).get();
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        var product = productRepository.getById(dto.getProductId()).get();
        if(product.isEmpty()){
           throw new ProductNotFoundException();
        }
        var basketPk = new ProductsInBasketsId();
        basketPk.setProduct(product.get());
        basketPk.setUser(user.get());
        entity.setPk(basketPk);
        entity.setProductQuantity(1);
        basketRepository.save(entity);
    }
    @Override
    @Async
    public void clearBasket() throws InterruptedException,ExecutionException
    {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var entity = basketRepository.getAllByUserId(principal.getId()).get();
        basketRepository.deleteAll(entity);
    }
    @Override
    @Async
    public CompletableFuture<List<ProductOutputDTO>> getProductsInBasket() throws InterruptedException,ExecutionException{
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var products = basketRepository.getAllByUserId(principal.getId()).get();
        var result = products.stream().map(e -> {
            var dto = new ProductOutputDTO();
            var product = e.getPk().getProduct();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setPrice(product.getPrice());
            dto.setProductStatus(product.getProductStatus());
            dto.setLeftInStock(product.getLeftInStock());
            dto.setCategoryId(product.getCategoryId());
            return dto;
        }).toList();
        return CompletableFuture.completedFuture(result);
    }

}
