package springApp.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApp.api.filters.ExceptionHandlingFilter;
import springApp.core.application.dto.basket.BasketCreateDTO;
import springApp.core.application.dto.product.ProductOutputDTO;
import springApp.core.application.interfaces.services.IBasketService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {
    @Autowired
    private IBasketService basketService;
    private final Logger logger = LoggerFactory.getLogger(BasketController.class);

    @Async
    @PostMapping
    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    public void addProductToBasket(@RequestBody BasketCreateDTO dto) throws InterruptedException, ExecutionException {
        basketService.addProductToBasket(dto);
    }

    @Async
    @DeleteMapping
    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    public void clearBasket() throws InterruptedException, ExecutionException {
        basketService.clearBasket();
    }

    @Async
    @GetMapping
    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    public CompletableFuture<List<ProductOutputDTO>> getProductsInBasket() throws InterruptedException, ExecutionException {

        return basketService.getProductsInBasket();
    }
}
