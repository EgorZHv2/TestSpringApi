package springApp.core.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import springApp.core.application.dto.product.ProductCreateDTO;
import springApp.core.application.dto.product.ProductOutputDTO;
import springApp.core.application.dto.product.ProductUpdateDTO;
import springApp.core.application.exceptions.CategoryNotFoundException;
import springApp.core.application.exceptions.ProductNotFoundException;
import springApp.core.application.interfaces.repositories.ICategoryRepository;
import springApp.core.application.interfaces.repositories.IProductRepository;
import springApp.core.application.interfaces.services.IProductService;
import springApp.core.domain.entities.Product;
import springApp.persistence.repositories.spring.interfaces.ICategorySpringRepository;
import springApp.persistence.repositories.spring.interfaces.IProductSpringRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    @Async
    public void create(ProductCreateDTO dto) throws InterruptedException,ExecutionException {
        var product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setProductStatus(dto.getProductStatus());
        product.setLeftInStock(dto.getLeftInStock());
        var categoryResult = categoryRepository.getById(dto.getCategoryId()).get();
        if (categoryResult.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        product.setCategory(categoryResult.get());
        productRepository.save(product);
    }

    @Override
    @Async
    public CompletableFuture<List<ProductOutputDTO>> getAll() throws InterruptedException, ExecutionException {
        var products = productRepository.getAll().get();
        var result = products.stream().map(e -> {
            var dto = new ProductOutputDTO();
            dto.setId(e.getId());
            dto.setName(e.getName());
            dto.setDescription(e.getDescription());
            dto.setPrice(e.getPrice());
            dto.setProductStatus(e.getProductStatus());
            dto.setLeftInStock(e.getLeftInStock());
            dto.setCategoryId(e.getCategoryId());
            return dto;
        }).toList();

        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<ProductOutputDTO> getById(UUID id) throws InterruptedException,ExecutionException {
        var entity = productRepository.getById(id).get();
        if (entity.isEmpty()) {
            throw new ProductNotFoundException();
        }
        var product = entity.get();
        var result = new ProductOutputDTO();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setDescription(product.getDescription());
        result.setPrice(product.getPrice());
        result.setProductStatus(product.getProductStatus());
        result.setLeftInStock(product.getLeftInStock());
        result.setCategoryId(product.getCategoryId());
        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public void update(ProductUpdateDTO dto) throws InterruptedException,ExecutionException {
        var entity = productRepository.getById(dto.getId()).get();
        if (entity.isEmpty()) {
            throw new ProductNotFoundException();
        }
        var product = entity.get();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setProductStatus(dto.getProductStatus());
        product.setLeftInStock(dto.getLeftInStock());
        var categoryResult = categoryRepository.getById(dto.getCategoryId()).get();
        if (categoryResult.isEmpty()) {
            throw new CategoryNotFoundException();
        }
        product.setCategory(categoryResult.get());
        productRepository.save(product);
    }

    @Override
    @Async
    public void delete(UUID id) throws InterruptedException,ExecutionException {
        var entity = productRepository.getById(id).get();
        if (entity.isEmpty()) {
            throw new ProductNotFoundException();
        }
        var product = entity.get();
        productRepository.delete(product);
    }


}
