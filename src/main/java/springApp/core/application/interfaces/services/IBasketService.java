package springApp.core.application.interfaces.services;

import org.springframework.security.access.prepost.PreAuthorize;
import springApp.core.application.dto.basket.BasketCreateDTO;
import springApp.core.application.dto.product.ProductOutputDTO;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IBasketService {

    void addProductToBasket(BasketCreateDTO dto) throws InterruptedException, ExecutionException;

    public CompletableFuture<List<ProductOutputDTO>> getProductsInBasket() throws InterruptedException, ExecutionException;
    void clearBasket() throws InterruptedException, ExecutionException;
}
