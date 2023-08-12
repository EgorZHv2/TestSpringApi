package springApp.core.application.interfaces.services;

import springApp.core.application.dto.product.ProductCreateDTO;
import springApp.core.application.dto.product.ProductOutputDTO;
import springApp.core.application.dto.product.ProductUpdateDTO;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IProductService {
    void create(ProductCreateDTO dto) throws InterruptedException, ExecutionException;

    CompletableFuture<List<ProductOutputDTO>> getAll() throws InterruptedException, ExecutionException;

    CompletableFuture<ProductOutputDTO> getById(UUID id) throws InterruptedException,ExecutionException;

    void update(ProductUpdateDTO dto) throws InterruptedException,ExecutionException;

    void delete(UUID id) throws InterruptedException,ExecutionException;
}
