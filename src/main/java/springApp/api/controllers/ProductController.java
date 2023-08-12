package springApp.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import springApp.core.application.dto.product.ProductCreateDTO;
import springApp.core.application.dto.product.ProductOutputDTO;
import springApp.core.application.dto.product.ProductUpdateDTO;
import springApp.core.application.interfaces.services.IProductService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    @Async
    public void create(@RequestBody ProductCreateDTO dto) throws InterruptedException, ExecutionException {
        productService.create(dto);
    }

    @GetMapping
    @Async
    public CompletableFuture<List<ProductOutputDTO>> getAll() throws InterruptedException, ExecutionException {
        return productService.getAll();
    }

    @Async
    @GetMapping("/{id}")
    public CompletableFuture<ProductOutputDTO> getById(@PathVariable UUID id) throws InterruptedException, ExecutionException {

        return productService.getById(id);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping
    @Async
    public void update(@RequestBody ProductUpdateDTO dto) throws InterruptedException, ExecutionException {
        productService.update(dto);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    @Async
    public void delete(@PathVariable UUID id) throws InterruptedException, ExecutionException {
        productService.delete(id);
    }
}
