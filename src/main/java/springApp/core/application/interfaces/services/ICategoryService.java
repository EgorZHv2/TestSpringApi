package springApp.core.application.interfaces.services;

import springApp.core.application.dto.category.CategoryCreateDTO;
import springApp.core.application.dto.category.CategoryOutputDTO;
import springApp.core.application.dto.category.CategoryUpdateDTO;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface ICategoryService {
    void create(CategoryCreateDTO dto) ;

    CompletableFuture<List<CategoryOutputDTO>> getAll() throws InterruptedException, ExecutionException;

    CompletableFuture<CategoryOutputDTO> getById(UUID id) throws InterruptedException,ExecutionException;

    void update(CategoryUpdateDTO dto) throws InterruptedException,ExecutionException;

    void delete(UUID id) throws InterruptedException,ExecutionException;

}
